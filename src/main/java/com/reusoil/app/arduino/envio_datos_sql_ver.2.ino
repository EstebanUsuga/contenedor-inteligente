#include <OneWire.h>
#include <DallasTemperature.h>
#include <ESP8266WiFi.h>
#include <ESP8266HTTPClient.h>

// Configuración Wi-Fi
const char* WIFI_SSID = "V2027";
const char* WIFI_PASSWORD = "Guayaba12..";

// URL del servidor para enviar datos
const char* urlEnviarDatos = "http://192.168.244.253:9090/resultado/guardar";

// Configuración de pines para NodeMCU V3
#define ONE_WIRE_BUS D2          // Pin para el sensor de temperatura DS18B20
#define TRIGGER_PIN D5           // Pin Trigger para el sensor ultrasónico
#define ECHO_PIN D6              // Pin Echo para el sensor ultrasónico

// Configuración del sensor de temperatura
OneWire oneWire(ONE_WIRE_BUS);
DallasTemperature sensors(&oneWire);

WiFiClient client;
HTTPClient http;

// Configuración del contenedor
const int alturaContenedor = 40;  // Altura total del contenedor en cm

void setup() {
  Serial.begin(115200);
  pinMode(TRIGGER_PIN, OUTPUT);
  pinMode(ECHO_PIN, INPUT);
  digitalWrite(TRIGGER_PIN, LOW);

  // Conexión Wi-Fi
  conectarWifi();

  sensors.begin();
  delay(1000);
}

void loop() {
  // Sensor ultrasónico: medir distancia
  long distancia = medirDistancia();
  Serial.print("Distancia: ");
  Serial.print(distancia);
  Serial.println(" cm");

  // Sensor de temperatura: leer temperatura
  float temp = leerTemperatura();
  Serial.print("Temperatura = ");
  Serial.print(temp);
  Serial.println(" °C");

  // Enviar datos al servidor SQL
  enviarDatosSQL(temp, distancia);

  delay(10000); // Pausa de 10 segundos entre lecturas
}

void conectarWifi() {
  Serial.println("Conectando a WiFi...");
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD);

  int retries = 0;
  while (WiFi.status() != WL_CONNECTED && retries < 20) {
    delay(500);
    Serial.print(".");
    retries++;
  }
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("No se pudo conectar al Wi-Fi");
  } else {
    Serial.println("\nConectado a Wi-Fi");
    Serial.print("IP: ");
    Serial.println(WiFi.localIP());
  }
}

long medirDistancia() {
  digitalWrite(TRIGGER_PIN, HIGH);
  delayMicroseconds(10);
  digitalWrite(TRIGGER_PIN, LOW);
  
  long t = pulseIn(ECHO_PIN, HIGH);
  return t / 59; // Convertimos el tiempo en distancia en cm
}

float leerTemperatura() {
  sensors.requestTemperatures();
  return sensors.getTempCByIndex(0); // Lee la temperatura en ºC
}

void enviarDatosSQL(float temp, float nivelLlenado) {
  if (WiFi.status() == WL_CONNECTED) {
    http.begin(client, urlEnviarDatos);
    http.addHeader("Content-Type", "application/json");

    String jsonPayload = "{\"resultadoTemperatura\":" + String(temp) + ",\"nivelLlenado\":" + String(nivelLlenado) + "}";
    
    int httpResponseCode = http.POST(jsonPayload);
    if (httpResponseCode == 200) {
      Serial.println("Datos enviados correctamente al servidor SQL");
    } else {
      Serial.print("Error al enviar datos: ");
      Serial.println(httpResponseCode);
    }

    http.end();
  } else {
    Serial.println("WiFi no conectado. Intentando reconectar...");
    conectarWifi();
  }
}

package com.reusoil.app.controller.server_info;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RestController
public class ServerInfoController {

    @GetMapping("/server-ip")
    public void printServerIp() {
        try {
            InetAddress ip = InetAddress.getLocalHost();
            System.out.println("La IP del servidor es: " + ip.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("No se pudo determinar la dirección IP del servidor.");
        }
    }
}

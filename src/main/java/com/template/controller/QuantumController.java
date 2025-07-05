package com.template.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/services")
public class QuantumController {

    @GetMapping("/run-quantum")
    public ResponseEntity<String> runQuantum(
        @RequestParam(defaultValue = "h") String gate,
        @RequestParam(defaultValue = "1000") int shots) {
        try {
            ProcessBuilder builder = new ProcessBuilder(
                "python", "quantum_runner.py", gate, String.valueOf(shots)
            );
            builder.directory(new File("C:\\Users\\Vishal10.Tiwari\\Desktop\\BITS_STUDY\\End Sem Exam\\Sem6\\Cloud_Computing\\QuantumComputing"));
            builder.redirectErrorStream(true);
            Process process = builder.start();

            BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream())
            );

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }

            int exitCode = process.waitFor();
            if (exitCode != 0) {
                return ResponseEntity.status(500).body("Quantum script failed");
            }

            return ResponseEntity.ok(result.toString());

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}

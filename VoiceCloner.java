import java.io.*;
import java.util.*;

public class VoiceCloner {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.err.println("❌ Uso: java VoiceCloner <texto> <voz_referencia.wav> <salida.wav> [idioma]");
            System.exit(1);
        }

        String text = args[0];
        String voicePath = args[1];
        String outputPath = args[2];
        String language = (args.length > 3) ? args[3] : "es";

        try {
            System.out.println("🔄 Java llamando a Python para clonar voz...");

            ProcessBuilder pb = new ProcessBuilder(
                "python3",
                "clonar_voz.py",
                "--text", text,
                "--voice", voicePath,
                "--output", outputPath,
                "--language", language
            );

            pb.redirectErrorStream(true);
            Process process = pb.start();

            // Mostrar salida en tiempo real
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("🎉 ¡Listo! Voz clonada en " + outputPath);
            } else {
                System.err.println("❌ Error al clonar la voz (código: " + exitCode + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

import argparse
from TTS.api import TTS

def main():
    parser = argparse.ArgumentParser(description="Clonador de voz XTTS-v2 (zero-shot)")
    parser.add_argument("--text", required=True, help="Texto a convertir en voz")
    parser.add_argument("--voice", required=True, help="Ruta al archivo de audio de referencia (.wav)")
    parser.add_argument("--output", required=True, help="Ruta del archivo de salida")
    parser.add_argument("--language", default="es", help="Idioma (es, en, fr, etc.)")
    args = parser.parse_args()

    print("🚀 Cargando modelo XTTS-v2 y clonando voz...")
    # Cargamos en CPU (GitHub Actions no tiene GPU)
    tts = TTS(model_name="tts_models/multilingual/multi-dataset/xtts_v2", 
              progress_bar=True, 
              gpu=False)

    tts.tts_to_file(
        text=args.text,
        speaker_wav=args.voice,
        language=args.language,
        file_path=args.output
    )
    print(f"✅ ¡Voz clonada con éxito! Archivo guardado en: {args.output}")

if __name__ == "__main__":
    main()

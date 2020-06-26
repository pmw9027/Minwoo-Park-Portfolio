import wave
import pyaudio
import sys

from util import load_config

config = load_config('config')

CHUNK = int(config['record']['chunk'])
FORMAT = pyaudio.paInt16
CHANNELS = int(config['record']['channels'])
RATE = int(config['record']['rate'])
RECORD_SECONDS = int(config['record']['record_seconds'])

try:
    WAVE_OUTPUT_FILENAME = sys.argv[1]
except:
    WAVE_OUTPUT_FILENAME = 'record/test.wav'
p = pyaudio.PyAudio()

stream = p.open(format=FORMAT,
            channels=CHANNELS,
            rate=RATE,
            input=True,
            frames_per_buffer=CHUNK)


print "* Recording audio..."

frames = []

for i in range(0, int(RATE / CHUNK * RECORD_SECONDS)):
    data = stream.read(CHUNK)
    frames.append(data)

print "* done\n"

stream.stop_stream()
stream.close()
p.terminate()


wf = wave.open(WAVE_OUTPUT_FILENAME, 'wb')
wf.setnchannels(CHANNELS)
wf.setsampwidth(p.get_sample_size(FORMAT))
wf.setframerate(RATE)
wf.writeframes(b''.join(frames))
wf.close()

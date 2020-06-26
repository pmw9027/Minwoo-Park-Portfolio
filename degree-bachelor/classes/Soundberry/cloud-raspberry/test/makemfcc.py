import sys

import numpy as np
import scipy.io.wavfile as wav
from python_speech_features import mfcc
try:
    input_wav = sys.argv[1]
except:
    input_wav = '../record/test.wav'

try:
    output = sys.argv[2]
except:
    output = '../mfcc/test'
(rate, sig) = wav.read(input_wav)

mfcc_feat = mfcc(sig, rate)
np.savetxt(output, mfcc_feat)
mfcc_feat2 = np.loadtxt(output)
print(mfcc_feat.ndim, mfcc_feat.shape)

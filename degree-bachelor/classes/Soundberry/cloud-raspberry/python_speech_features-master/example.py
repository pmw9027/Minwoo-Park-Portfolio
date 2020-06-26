#!/usr/bin/env python

import scipy.io.wavfile as wav

from raspberry.python_speech_features import logfbank
from raspberry.python_speech_features import mfcc

(rate,sig) = wav.read("english.wav")
mfcc_feat = mfcc(sig,rate)
fbank_feat = logfbank(sig,rate)

print(fbank_feat[1:3,:])

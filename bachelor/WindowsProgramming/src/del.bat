attrib -H *.suo
del /S /Q *.sdf *.exp *.ilk *.pdb *.ncb *.obj *.pch *.idb *.ipch *.suo *.user *.aps
rd /S /Q ipch
rd /S /Q Debug
rd /S /Q Release
rd /S /Q .vs
rd /S /Q x64

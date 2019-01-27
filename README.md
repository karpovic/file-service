# file-service
Service provide rest API for work with files.
There is an option to upload files as many as you need. Service will process files count all words and split it in four files by letters intervals (A-G, H-N, O-U, V-Z).

# services

1) uploadFile: upload txt file;
2) getStatus: provide number of uploaded files and number of different words;
3) There are 4 services to get words by intervals (1: A-G, 2: H-N, 3: O-U, 4: V-Z) will return as string:
    a) getWordsByInterval1: A-G;
    b) getWordsByInterval2: H-N;
    c) getWordsByInterval3: O-U;
    d) getWordsByInterval4: V-Z;
4) getFileByIntervalNumber: let to download file by provide interval (1: A-G, 2: H-N, 3: O-U, 4: V-Z);
5) resetSystem: reset all calculations;

# links
https://karpovic-file-service.herokuapp.com/

# QRCode Service
In this cool Spring Boot project I've  learned how to  work with the ZXing library, an open-source library, to encode information and create QR codes.
Request parameters include:
* Image size (max 350, min 150) 
* Type(png, jpeg or gif)
* Message to be encoded(must not be empty or blank) 
* And four error correction levels, determining how much code can be damaged or obscured while still readable (Level L (Low), Level M (Medium), Level Q (Quartile), Level H (High)).
  
If there is no errors in the request, the program will generate a QRCode image:

![image](https://github.com/guiuba/QRCodeService/assets/69851038/800b0d6f-0c48-4690-bd6f-5375493b7a48)

Otherwise, the program will return a Json message, specifying the error in the request. Examples:







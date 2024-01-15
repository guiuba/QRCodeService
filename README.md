# QRCode Service
In this cool Spring Boot project I've  learned how to  work with the ZXing library, an open-source library, to encode information and create QR codes.
Request parameters include:
* Image size (max 350, min 150) 
* Type(png, jpeg or gif)
* Contents - message to be encoded(must not be empty or blank) 
* And four error correction levels, determining how much code can be damaged or obscured while still readable (Level L (Low), Level M (Medium), Level Q (Quartile), Level H (High)).
  
If there is no errors in the request, the program will generate a QRCode image.

Otherwise, the program will return a Json message, specifying the error in the request. Examples:

![image](https://github.com/guiuba/QRCodeService/assets/69851038/c92967dd-b817-423c-950e-b75e3cb550ff)

![image](https://github.com/guiuba/QRCodeService/assets/69851038/800b0d6f-0c48-4690-bd6f-5375493b7a48)

![image](https://github.com/guiuba/QRCodeService/assets/69851038/d2c67e0e-a8aa-41bd-963a-291b4c0eb1bc)







var exec = require('cordova/exec');

var printer = function(){};

printer.prototype.init = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'init', arg0);
};
printer.prototype.isConnected = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'isConnected', arg0);
};
printer.prototype.printStatus = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'printStatus', arg0);
};
printer.prototype.SetCommmandmode = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetCommmandmode', arg0);
};
printer.prototype.SetClean = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetClean', arg0);
};
printer.prototype.SetLinespace = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetLinespace', arg0);
};
printer.prototype.SetSpacechar = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetSpacechar', arg0);
};
printer.prototype.SetSpacechinese = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetSpacechinese', arg0);
};
printer.prototype.SetLeftmargin = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetLeftmargin', arg0);
};
printer.prototype.SetMarkoffsetcut = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetMarkoffsetcut', arg0);
};
printer.prototype.SetSizechinese = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetSizechinese', arg0);
};
printer.prototype.SetSizechar = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetSizechar', arg0);
};
printer.prototype.SetSizetext = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetSizetext', arg0);
};
printer.prototype.SetAlignment = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetAlignment', arg0);
};
printer.prototype.SetBold = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetBold', arg0);
};
printer.prototype.SetDirection = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetDirection', arg0);
};
printer.prototype.SetWhitemodel = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetWhitemodel', arg0);
};
printer.prototype.SetItalic = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetItalic', arg0);
};
printer.prototype.setUnderline = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'setUnderline', arg0);
};
printer.prototype.SetReadZKmode = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetReadZKmode', arg0);
};
printer.prototype.SetHTseat = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetHTseat', arg0);
};
printer.prototype.SetCodepage = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetCodepage', arg0);
};
printer.prototype.SetNvbmp = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetNvbmp', arg0);
};
printer.prototype.SetRightmargin = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'SetRightmargin', arg0);
};
printer.prototype.Set1DBarCodeAlign = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'Set1DBarCodeAlign', arg0);
};
printer.prototype.PrintFeedline = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintFeedline', arg0);
};
printer.prototype.PrintString = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintString', arg0);
};
printer.prototype.PrintChargeRow = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintChargeRow', arg0);
};
printer.prototype.PrintFeedDot = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintFeedDot', arg0);
};
printer.prototype.PrintNextHT = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintNextHT', arg0);
};
printer.prototype.PrintCutpaper = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintCutpaper', arg0);
};
printer.prototype.PrintMarkposition = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintMarkposition', arg0);
};
printer.prototype.PrintMarkpositionprint = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintMarkpositionprint', arg0);
};
printer.prototype.PrintMarkpositioncut = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintMarkpositioncut', arg0);
};
printer.prototype.PrintMarkcutpaper = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintMarkcutpaper', arg0);
};
printer.prototype.PrintQrcode = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintQrcode', arg0);
};
printer.prototype.PrintQrCodeT500II = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintQrCodeT500II', arg0);
};
printer.prototype.PrintPdf417 = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintPdf417', arg0);
};
printer.prototype.Print1Dbar = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'Print1Dbar', arg0);
};
printer.prototype.PrintDiskbmpfile = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintDiskbmpfile', arg0);
};
 printer.prototype.PrintDiskImagefile = function (arg0, success, error) {
 	exec(success, error, 'masungPlugin', 'PrintDiskImagefile', arg0);
 };
printer.prototype.PrintNvbmp = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'PrintNvbmp', arg0);
};
printer.prototype.GetStatus = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'GetStatus', arg0);
};
printer.prototype.GetSDKinformation = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'GetSDKinformation', arg0);
};
printer.prototype.closeUsbDevice = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'closeUsbDevice', arg0);
};
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
printer.prototype.ESetHTseat = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'ESetHTseat', arg0);
};
printer.prototype.CloseReceiver = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'CloseReceiver', arg0);
};
printer.prototype.printReceipt = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'printReceipt', arg0);
};
printer.prototype.printReceiptPayAtCounter = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'printReceiptPayAtCounter', arg0);
};
printer.prototype.printReceiptKitchen = function (arg0, success, error) {
	exec(success, error, 'masungPlugin', 'printReceiptKitchen', arg0);
};
var masungPrinter = new printer();
module.exports = masungPrinter; 

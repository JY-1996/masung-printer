package masung.printer.plugin;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.widget.Toast;

import com.printsdk.cmd.PrintCmd;
import com.printsdk.usbsdk.UsbDriver;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
/**
 * This class echoes a string called from JavaScript.
 */
public class masungPlugin extends CordovaPlugin {

    private static final String ACTION_USB_PERMISSION = "masung.printer.plugin.USB_PERMISSION";
    private UsbManager mUsbManager;
    UsbDriver mUsbDriver;
    UsbDevice mUsbDev1;
    UsbDevice mUsbDev2;
    private final static int PID11 = 8211;
    private final static int PID13 = 8213;
    private final static int PID15 = 8215;
    private final static int VENDORID = 1305;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        switch (action) {
            case "init":
            cordova.getThreadPool().execute(this::init);
            return true;
            case "isConnected":
            cordova.getThreadPool().execute(() -> isConnected(callbackContext));
            return true;
            case "printStatus":
            cordova.getThreadPool().execute(() -> printConnStatus(callbackContext));
            return true;
            case "SetCommmandmode":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetCommmandmode(args.getInt(0)), mUsbDev1);
                        // int iMode : 2 EPIC Mode、3 EPOS Mode
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetClean":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetClean(), mUsbDev1);
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetLinespace":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetLinespace(args.getInt(0)), mUsbDev1);
                        // int iLinespace：line spacing, value is 0-127，units is 0.125mm
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetSpacechar":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetSpacechar(args.getInt(0)), mUsbDev1);
                        // int iSpace：character pitch, value is 0-64, units is 0.125mm
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetSpacechinese":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetSpacechinese(args.getInt(0), args.getInt(1)), mUsbDev1);
                        // int iChsleftspace：left of Chinese is empty, values is 0-64, units is 0.125mm
                        // int iChsrightspace：right of Chinese is empty, value is 0-64,units is 0.125mm
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetLeftmargin":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetLeftmargin(args.getInt(0)), mUsbDev1);
                        // int iLeftspace：character space, value is0-576, units is 0.125mm
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetMarkoffsetcut":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetMarkoffsetcut(args.getInt(0)), mUsbDev1);
                        // int iOffset：Offset，value is 0-1600
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetSizechinese":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetSizechinese(args.getInt(0), args.getInt(1), args.getInt(2), args.getInt(3)), mUsbDev1);
                        // int iHeight：Double-height 0 invalid 1 valid
                        // int iWidth：Double-width 0 invalid 1 valid
                        // int iUnderline： Undefine 0 invalid 1 valid
                        // int iChinesetype：chinese type 0 24*24 1 16*16
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetSizechar":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetSizechar(args.getInt(0), args.getInt(1), args.getInt(2), args.getInt(3)), mUsbDev1);
                        // int iHeight：Double-height 0 invalid 1 valid
                        // int iWidth：Double-width 0 invalid 1 valid
                        // int iUnderline： Underfine 0 invalid 1 valid
                        // int iAsciitype： ASCII 0 12*24 1 9*17
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetSizetext":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetSizetext(args.getInt(0), args.getInt(1)), mUsbDev1);
                        // int iHeight：enlarge height, value is (1-8)
                        // int iWidth：enlarge width，value is (1-8)
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetAlignment":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetAlignment(args.getInt(0)), mUsbDev1);
                        // int iAlignment：0 left、1 center、2 right
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetBold":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetBold(args.getInt(0)), mUsbDev1);
                        // int iBold：0 No 1 yes
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetDirection":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetDirection(args.getInt(0)), mUsbDev1);
                        // int iDirection： 0 From left to right、1 rotate 180°
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetWhitemodel":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetWhitemodel(args.getInt(0)), mUsbDev1);
                        // int iWhite：0 cancel white; 1 white
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetItalic":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetItalic(args.getInt(0)), mUsbDev1);
                        // int iItalic：0 cancel italic; 1 italic
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "setUnderline":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetUnderline(args.getInt(0)), mUsbDev1);
                        // int underline：0 cancel underline 1 one point underline ; 2 two point underline
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetReadZKmode":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetReadZKmode(args.getInt(0)), mUsbDev1);
                        // int mode：0 to enter the Chinese character mode; 1 exit the Chinese character mode
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;

            ////////////////////////////////////////////////
//            have problem
            case "SetHTseat":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetHTseat(args.getString(0).getBytes(), args.getInt(1)), mUsbDev1);
                        // byte[] bHTseat：The location of the horizontal tab from small to large units of a ASCII character can not be 0.
                        // int iLength: Horizontal tab of the number of location data.
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;

            ///////////////////////////////////////////////////
            case "SetCodepage":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetCodepage(args.getInt(0), args.getInt(1)), mUsbDev1);
                        // int country：National font
                        // int CPnumber: Code page
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetNvbmp":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetNvbmp(args.getInt(0), args.getString(1)), mUsbDev1);
                        // int iNums：Bit image quantity(single file support 64k(max); total files support 192K)
                        // const char* strPath：image file path（If only file name then please use current route, if specify all routes then use the specified route），use ”;”separate，quantities need be same with iNums parameters.
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "SetRightmargin":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.SetRightmargin(args.getInt(0)), mUsbDev1);
                        // int iRightspace：right margin , value is 0 - 255
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "Set1DBarCodeAlign":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.Set1DBarCodeAlign(args.getInt(0)), mUsbDev1);
                        // int iAlign：Align type, 0 left, 1 center, 2 right
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintFeedline":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintFeedline(args.getInt(0)), mUsbDev1);
                        // int iLine：Paper feed lines
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintString":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintString(args.getString(0), args.getInt(1)), mUsbDev1);
                        // String strData：Print character string content
                        // int iImme;Whether the newline instruction 0x0a:0 add a line directive 1 without newline (instruction until the next newline is print )
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintChargeRow":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintChargeRow(), mUsbDev1);
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintFeedDot":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintFeedDot(args.getInt(0)), mUsbDev1);
                        // int Lnumber:value is 0-250
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintNextHT":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintNextHT(), mUsbDev1);
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintCutpaper":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintCutpaper(args.getInt(0)), mUsbDev1);
                        // int iMode：0 Full cut、1 Partial cut
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintMarkposition":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintMarkposition(), mUsbDev1);
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintMarkpositionprint":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintMarkpositionprint(), mUsbDev1);
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintMarkpositioncut":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintMarkpositioncut(), mUsbDev1);
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintMarkcutpaper":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintMarkcutpaper(args.getInt(0)), mUsbDev1);
                        // int iMode：0 Detect BM paper full cut、1 No detect BM paper partial cut
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintQrcode":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintQrcode(args.getString(0), args.getInt(1), args.getInt(2), args.getInt(3)), mUsbDev1);
                        // String strData：Content
                        // int iLmargin：left margin, value is 0-27 units mm
                        // int iMside：Units length,，that is QR code size, value is 1-8，（some printers only support 1-4）
                        // int iRound：Surrounded mode,0 print immediately（No mixed row）、1 surround（mixed row, some printers don’t support）
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintQrCodeT500II":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintQrCodeT500II(args.getInt(0), args.getString(1)), mUsbDev1);
                        // int iSide：Units length,，that is QR code size, value is 1-9
                        // String strData：Content
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintPdf417":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintPdf417(args.getInt(0), args.getInt(1), args.getInt(2), args.getInt(3), args.getString(4)), mUsbDev1);
                        // int iDotwidth：width, value is 0-255
                        // int iDotheight：height, value is 0-255
                        // int iDatarows：line number
                        // int iDatacolumns: row number
                        // String strData: content
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "Print1Dbar":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.Print1Dbar(args.getInt(0), args.getInt(1), args.getInt(2), args.getInt(3), args.getInt(4), args.getString(5)), mUsbDev1);
                        // int iWidth：code width, value is 2-6 units 0.125mm
                        // int iHeight： code height, value is 1-255 units 0.125mm
                        // int iHrisize： code show character font 0 12*24 1 9*17
                        // int iHriseat： code show character position 0 none 1 up 2 down 3 up and down
                        // int iCodetype：code type
                        // （* UPC-A 0,* UPC-E 1,* EAN132,* EAN83,
                        // * CODE394,* ITF5,* CODABAR6,* Standard EAN137,
                        // * Standard EAN88,* CODE93 9,* CODE128 10)
                        // String strData：code content
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintDiskbmpfile":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintDiskbmpfile(args.getString(0)), mUsbDev1);
                        // String strPath：file path
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "PrintDiskImagefile":
            cordova.getThreadPool().execute(new Runnable() {
               public void run() {
                   try{
                       String[] items = args.getString(0).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\s", "").split(",");
                       int[] results = new int[items.length];

                       for (int i = 0; i < items.length; i++) {
                           try {
                               results[i] = Integer.parseInt(items[i]);
                           } catch (NumberFormatException nfe) {
                                     //NOTE: write something here if you need to recover from formatting errors
                           };
                       }
                       mUsbDriver.write(PrintCmd.PrintDiskImagefile(results,args.getInt(1),args.getInt(2)),mUsbDev1);
                             // int[] pixels：image pixel array
                             // int iWidth： image width
                             // int iHeight：image height
                             // ex: "[10,11,12]",13,14
                       callbackContext.success("true");
                   }catch(Exception e){
                    callbackContext.error(e.getMessage());
                }
            }
        });
            return true;
            case "PrintNvbmp":
            cordova.getThreadPool().execute(() -> {
                try {
                    mUsbDriver.write(PrintCmd.PrintNvbmp(args.getInt(0), args.getInt(1)), mUsbDev1);
                        // int iNvindex：NV bit image index
                        // int iMode：48 normal、49 double-width、50 double-height、51 double-width、double-height (Quadruple)
                    callbackContext.success("true");
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "GetStatus":
            cordova.getThreadPool().execute(() -> {
                try {
                    callbackContext.success(PrintCmd.GetStatus());
                        // value
                        // 0 printer normal
                        // 1 printer is not connected or not on the power
                        // 2 printer and library does not match
                        // 3 print head open
                        // 4 cutting knife is not reset
                        // 5 print head overheating
                        // 6 black mark error
                        // 7 paper Exhausted
                        // 8 paper will Exhausted
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "GetSDKinformation":
            cordova.getThreadPool().execute(() -> {
                try {
                    callbackContext.success(PrintCmd.GetSDKinformation());
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }
            });
            return true;
            case "ESetHTseat":
            cordova.getThreadPool().execute(() -> {
                setHTseat(args, callbackContext);
            });
            return true;
            case "CloseReceiver":
            cordova.getThreadPool().execute(() -> {
                closeReceiver(callbackContext);
            });
            return true;
            case "printReceipt":
            cordova.getThreadPool().execute(() -> {
                try {
                    printReceipt(callbackContext,args.getString(0));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }

            });
            return true;
            case "printReceiptPayAtCounter":
            cordova.getThreadPool().execute(() -> {
                try {
                    printReceiptPayAtCounter(callbackContext,args.getString(0));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }

            });
            return true;
            case "printReceiptKitchen":
            cordova.getThreadPool().execute(() -> {
                try {
                    printReceiptKitchen(callbackContext,args.getString(0));
                } catch (Exception e) {
                    callbackContext.error(e.getMessage());
                }

            });
            return true;

        }
        return false;
    }

    public void setHTseat(JSONArray args, CallbackContext callbackContext) {
        char[] ch = new char[args.length()];
        for (int i = 0; i < args.length(); i++) {
            try {
                ch[i] = (char) args.getInt(i);
                Charset charset = StandardCharsets.UTF_8;
                ByteBuffer byteBuffer = charset.encode(CharBuffer.wrap(ch));
                byte[] cSeat = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
                mUsbDriver.write(PrintCmd.SetHTseat(cSeat, cSeat.length));
                callbackContext.success("true");
            } catch (JSONException e) {
                e.printStackTrace();
                callbackContext.error(e.getMessage());
            }
        }
    }

    private void init() {
        mUsbManager = (UsbManager) cordova.getActivity().getSystemService(Context.USB_SERVICE);
        mUsbDriver = new UsbDriver(mUsbManager, cordova.getActivity());
        PendingIntent permissionIntent1 = PendingIntent.getBroadcast(cordova.getActivity(), 0,
            new Intent(ACTION_USB_PERMISSION), 0);
        mUsbDriver.setPermissionIntent(permissionIntent1);
        IntentFilter filter = new IntentFilter();
        filter.addAction(ACTION_USB_PERMISSION);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        cordova.getActivity().registerReceiver(mUsbReceiver, filter);
    }
    private void closeReceiver(CallbackContext callbackContext){
        try{
            cordova.getActivity().unregisterReceiver(mUsbReceiver);  
            callbackContext.success("mUsbDev");
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
        }
    }
    private void printConnStatus(CallbackContext callbackContext) {
        boolean blnRtn;
        try {
            if (!mUsbDriver.isConnected()) {
                for (UsbDevice device : mUsbManager.getDeviceList().values()) {
                    if ((device.getProductId() == PID11 && device.getVendorId() == VENDORID)
                        || (device.getProductId() == PID13 && device.getVendorId() == VENDORID)
                        || (device.getProductId() == PID15 && device.getVendorId() == VENDORID)) {
                        if (!mUsbManager.hasPermission(device)) {
                            PendingIntent permissionIntent1 = PendingIntent.getBroadcast(cordova.getActivity(), 0,
                                new Intent(ACTION_USB_PERMISSION), 0);
                            mUsbManager.requestPermission(device, permissionIntent1);
                        }
                        blnRtn = mUsbDriver.usbAttached(device);
                        if (!blnRtn) {
                            break;
                        }
                        blnRtn = mUsbDriver.openUsbDevice(device);
                        if (blnRtn) {
                            if (device.getProductId() == PID11) {
                                mUsbDev1 = device;

                            } else {
                                mUsbDev2 = device;
                            }
                            callbackContext.success("mUsbDev");
                        } else {
                            callbackContext.error("failed");
                        }
                        break;
                    }
                }
            } else {
                callbackContext.success("connected");
            }
        } catch (Exception e) {
            callbackContext.error(e.getMessage());
        }

    }

    private void isConnected(CallbackContext callbackContext) {
        if (mUsbDriver.isConnected()) {
            callbackContext.success("conn true");
        } else {
            callbackContext.success("conn false");
        }
    }

    BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            try {
                if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
                    if (mUsbDriver.usbAttached(intent)) {
                        UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                        if ((device.getProductId() == PID11 && device.getVendorId() == VENDORID)
                            || (device.getProductId() == PID13 && device.getVendorId() == VENDORID)
                            || (device.getProductId() == PID15 && device.getVendorId() == VENDORID)) {
                            if (mUsbDriver.openUsbDevice(device)) {
                                if (device.getProductId() == PID11) {
                                    mUsbDev1 = device;
                                } else {
                                    mUsbDev2 = device;
                                }
                            }
                        }
                    }
                } else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if ((device.getProductId() == PID11 && device.getVendorId() == VENDORID)
                        || (device.getProductId() == PID13 && device.getVendorId() == VENDORID)
                        || (device.getProductId() == PID15 && device.getVendorId() == VENDORID)) {
                        mUsbDriver.closeUsbDevice(device);
                    if (device.getProductId() == PID11)
                        mUsbDev1 = null;
                    else
                        mUsbDev2 = null;
                }
            } else if (ACTION_USB_PERMISSION.equals(action)) {
                synchronized (this) {
                    UsbDevice device = (UsbDevice) intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if ((device.getProductId() == PID11 && device.getVendorId() == VENDORID) || (device.getProductId() == PID13 && device.getVendorId() == VENDORID) || (device.getProductId() == PID15 && device.getVendorId() == VENDORID)) {
                            if (mUsbDriver.openUsbDevice(device)) {
                                if (device.getProductId() == PID11) {
                                    mUsbDev1 = device;
                                } else {
                                    mUsbDev2 = device;
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
};
private void printReceipt(CallbackContext callbackContext,String message) {
    try {
        JSONObject json = new JSONObject(message);
        mUsbDriver.write(PrintCmd.SetClean());
        mUsbDriver.write(PrintCmd.SetAlignment(1));
        mUsbDriver.write(PrintCmd.SetBold(1));
        mUsbDriver.write(PrintCmd.SetSizetext(1,1));
        mUsbDriver.write(PrintCmd.PrintString(json.getJSONObject("shop").getString("name"), 0));
        mUsbDriver.write(PrintCmd.SetBold(0));
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("TEL: "+json.getJSONObject("shop").getString("phone_no"), 0));
        mUsbDriver.write(PrintCmd.PrintString("ORDER ID: "+json.getString("order_no"), 0));
        mUsbDriver.write(PrintCmd.PrintString(json.getString("created_at"), 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.SetBold(1));
        mUsbDriver.write(PrintCmd.SetSizetext(1,1));
        if(json.getString("number_type") == "table"){
            mUsbDriver.write(PrintCmd.PrintString("Table No", 0));
        }else if(json.getString("number_type") == "pager"){
            mUsbDriver.write(PrintCmd.PrintString("Pager No", 0));
        }else{
            mUsbDriver.write(PrintCmd.PrintString("Queue No", 0));
        }
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.SetSizetext(2,2));
        mUsbDriver.write(PrintCmd.PrintString(json.getString("number"), 0));
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.SetBold(0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        mUsbDriver.write(PrintCmd.SetAlignment(0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        char[] cSeat = {5, 40};
        mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat), charsToBytes(cSeat).length));
        mUsbDriver.write(PrintCmd.PrintString("QTY", 1));
        mUsbDriver.write(PrintCmd.PrintNextHT());
        mUsbDriver.write(PrintCmd.PrintString("ITEM", 1));
        mUsbDriver.write(PrintCmd.PrintNextHT());
        mUsbDriver.write(PrintCmd.PrintString("AMT(RM))", 1));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));

        for (int i = 0; i < json.getJSONArray("line_items").length(); i++) {
            mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat), charsToBytes(cSeat).length));
            JSONObject food = json.getJSONArray("line_items").getJSONObject(i);
            String name = food.getString("name");
            String[] namelist = splitStringEvery(name,32);
            mUsbDriver.write(PrintCmd.PrintString(food.getString("quantity"), 1));
            mUsbDriver.write(PrintCmd.PrintNextHT());
            mUsbDriver.write(PrintCmd.PrintString(namelist[0], 1));
            mUsbDriver.write(PrintCmd.PrintNextHT());
            mUsbDriver.write(PrintCmd.PrintString(setPosition(food.getString("sub_total")), 1));
            if(namelist.length>1){
                for(int j = 1; j < namelist.length; j++){
                    mUsbDriver.write(PrintCmd.PrintChargeRow());
                    mUsbDriver.write(PrintCmd.PrintNextHT());
                    mUsbDriver.write(PrintCmd.PrintString(namelist[j], 1));
                }
            }
            mUsbDriver.write(PrintCmd.PrintChargeRow());
            for (int k = 0; k < food.getJSONArray("options").length(); k++) {
                char[] cSeat1 = {7};
                mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat1), charsToBytes(cSeat1).length));
                JSONObject options = food.getJSONArray("options").getJSONObject(k);
                for (int l = 0; l < options.getJSONArray("items").length(); l++) {
                    JSONObject item = options.getJSONArray("items").getJSONObject(l);
                    String[] itemList = splitStringEvery(item.getString("name"),28);
                    for (String s : itemList) {
                        mUsbDriver.write(PrintCmd.PrintNextHT());
                        mUsbDriver.write(PrintCmd.PrintString(s, 1));
                        mUsbDriver.write(PrintCmd.PrintChargeRow());
                    }
                }
            }
        }
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        char[] cSeat2 = {40};
        mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat2), charsToBytes(cSeat2).length));
        mUsbDriver.write(PrintCmd.PrintString("SUBTOTAL", 1));
        mUsbDriver.write(PrintCmd.PrintNextHT());
        mUsbDriver.write(PrintCmd.PrintString(setPosition(json.getString("total_price")), 0));
        // mUsbDriver.write(PrintCmd.PrintString("Tax", 1));
        // mUsbDriver.write(PrintCmd.PrintNextHT());
        // mUsbDriver.write(PrintCmd.PrintString("  0.00", 0));
        mUsbDriver.write(PrintCmd.PrintString("DISCOUNT", 1));
        mUsbDriver.write(PrintCmd.PrintNextHT());
        mUsbDriver.write(PrintCmd.PrintString(setPosition(json.getString("total_discount")), 0));
        mUsbDriver.write(PrintCmd.PrintString("TOTAL", 1));
        mUsbDriver.write(PrintCmd.PrintNextHT());
        mUsbDriver.write(PrintCmd.PrintString(setPosition(json.getString("total_price")), 0));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        mUsbDriver.write(PrintCmd.SetAlignment(1));
        mUsbDriver.write(PrintCmd.PrintQrcode("http://wecafe.uat.wepost.com.my/home",23,7, 1) ,mUsbDev1);
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.PrintFeedline(2));
        mUsbDriver.write(PrintCmd.PrintString("SCAN TO REVIEW OUR WEBSITE", 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("THANK YOU", 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(10));
        mUsbDriver.write(PrintCmd.PrintCutpaper(1));
        callbackContext.success("true");
    } catch (Exception e) {
     callbackContext.error(e.getMessage());
     e.printStackTrace();
 }
}
private void printReceiptPayAtCounter(CallbackContext callbackContext,String message) {
    try {
        JSONObject json = new JSONObject(message);
        mUsbDriver.write(PrintCmd.SetClean());
        mUsbDriver.write(PrintCmd.SetAlignment(1));
        mUsbDriver.write(PrintCmd.SetBold(1));
        mUsbDriver.write(PrintCmd.SetSizetext(1,1));
        mUsbDriver.write(PrintCmd.PrintString(json.getJSONObject("shop").getString("name"), 0));
        mUsbDriver.write(PrintCmd.SetBold(0));
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("TEL: "+json.getJSONObject("shop").getString("phone_no"), 0));
        mUsbDriver.write(PrintCmd.PrintString(json.getString("created_at"), 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("PLEASE PAY AT COUNTER", 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.SetBold(1));  
        mUsbDriver.write(PrintCmd.SetSizetext(1,1));
        mUsbDriver.write(PrintCmd.PrintString("Order Id", 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.SetSizetext(2,2)); 
        mUsbDriver.write(PrintCmd.PrintString(json.getString("order_no"), 0));
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.SetBold(0));
        mUsbDriver.write(PrintCmd.PrintFeedline(10));
        mUsbDriver.write(PrintCmd.PrintCutpaper(1));
        callbackContext.success("true");
    } catch (Exception e) {
     callbackContext.error(e.getMessage());
     e.printStackTrace();
 }
}

private void printReceiptKitchen(CallbackContext callbackContext,String message) {
    try {
        JSONObject json = new JSONObject(message);
        mUsbDriver.write(PrintCmd.SetClean());
        mUsbDriver.write(PrintCmd.SetAlignment(1));
        mUsbDriver.write(PrintCmd.SetBold(1));
        mUsbDriver.write(PrintCmd.SetSizetext(1,1));
        mUsbDriver.write(PrintCmd.PrintString(json.getJSONObject("shop").getString("name"), 0));
        mUsbDriver.write(PrintCmd.SetBold(0));
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString(json.getString("created_at"), 0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        mUsbDriver.write(PrintCmd.SetAlignment(0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        char[] cSeat = {5};
        mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat), charsToBytes(cSeat).length));
        mUsbDriver.write(PrintCmd.PrintString("QTY", 1));
        mUsbDriver.write(PrintCmd.PrintNextHT());
        mUsbDriver.write(PrintCmd.PrintString("ITEM", 1));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));

        for (int i = 0; i < json.getJSONArray("line_items").length(); i++) {
            mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat), charsToBytes(cSeat).length));
            JSONObject food = json.getJSONArray("line_items").getJSONObject(i);
            String name = food.getString("name");
            String[] namelist = splitStringEvery(name,40);
            mUsbDriver.write(PrintCmd.PrintString(food.getString("quantity"), 1));
            mUsbDriver.write(PrintCmd.PrintNextHT());
            mUsbDriver.write(PrintCmd.PrintString(namelist[0], 1));
            if(namelist.length>1){
                for(int j = 1; j < namelist.length; j++){
                    mUsbDriver.write(PrintCmd.PrintChargeRow());
                    mUsbDriver.write(PrintCmd.PrintNextHT());
                    mUsbDriver.write(PrintCmd.PrintString(namelist[j], 1));
                }
            }
            mUsbDriver.write(PrintCmd.PrintChargeRow());
            for (int k = 0; k < food.getJSONArray("options").length(); k++) {
                char[] cSeat1 = {7};
                mUsbDriver.write(PrintCmd.SetHTseat(charsToBytes(cSeat1), charsToBytes(cSeat1).length));
                JSONObject options = food.getJSONArray("options").getJSONObject(k);
                for (int l = 0; l < options.getJSONArray("items").length(); l++) {
                    JSONObject item = options.getJSONArray("items").getJSONObject(l);
                    String[] itemList = splitStringEvery(item.getString("name"),38);
                    for (String s : itemList) {
                        mUsbDriver.write(PrintCmd.PrintNextHT());
                        mUsbDriver.write(PrintCmd.PrintString(s, 1));
                        mUsbDriver.write(PrintCmd.PrintChargeRow());
                    }
                }
            }
        }
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString("-----------------------------------------------", 0));
        mUsbDriver.write(PrintCmd.SetAlignment(1));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.SetBold(1));
        mUsbDriver.write(PrintCmd.SetSizetext(1,1));
        if(json.getString("number_type") == "table"){
            mUsbDriver.write(PrintCmd.PrintString("Table No", 0));
        }else if(json.getString("number_type") == "pager"){
            mUsbDriver.write(PrintCmd.PrintString("Pager No", 0));
        }else{
            mUsbDriver.write(PrintCmd.PrintString("Queue No", 0));
        }
        mUsbDriver.write(PrintCmd.SetSizetext(2,2));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintString(json.getString("number"), 0));
        mUsbDriver.write(PrintCmd.SetSizetext(0,0));
        mUsbDriver.write(PrintCmd.SetBold(0));
        mUsbDriver.write(PrintCmd.PrintFeedline(1));
        mUsbDriver.write(PrintCmd.PrintFeedline(10));
        mUsbDriver.write(PrintCmd.PrintCutpaper(1));
        callbackContext.success("true");
    } catch (Exception e) {
     callbackContext.error(e.getMessage());
     e.printStackTrace();
 }
}
public String[] splitStringEvery(String s, int interval) {
    int arrayLength = (int) Math.ceil(((s.length() / (double)interval)));
    String[] result = new String[arrayLength];

    int j = 0;
    int lastIndex = result.length - 1;
    for (int i = 0; i < lastIndex; i++) {
        result[i] = s.substring(j, j + interval);
        j += interval;
    }
    result[lastIndex] = s.substring(j);

    return result;
}
public byte[] charsToBytes(char[] chars) {
    Charset charset = StandardCharsets.UTF_8;
    ByteBuffer byteBuffer = charset.encode(CharBuffer.wrap(chars));
    return Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
}
public String setPosition(String value) {
    if(value.length()==4){
        return "  " + value;
    }else if(value.length()==5){
        return " " + value;
    }
    return value;
}
}

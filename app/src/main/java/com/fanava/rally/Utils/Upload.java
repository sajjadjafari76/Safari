package com.fanava.rally.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

//import com.fanava.rally.server.ShowData;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Upload {
//    private Context context;
//    public String DoUpload(Context context1, String url, Uri file,String Id)
//    {
//        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//        StrictMode.setThreadPolicy(policy);
//        this.context=context1;
//        String iFileName = Id+".jpg";
//        String lineEnd = "\r\n";
//        String twoHyphens = "--";
//        String boundary = "*****";
//        String Tag = "fSnd";
//        String s = "";
//        try {
//
//
//                Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), file);
//                bitmap = Bitmap.createScaledBitmap(bitmap, 250, 200, true);
//                ByteArrayOutputStream bos = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, bos);
//                byte[] bitmapdata = bos.toByteArray();
//
//                ByteArrayInputStream stream = new ByteArrayInputStream(bitmapdata);
//
//           // InputStream stream = context.getContentResolver().openInputStream(file);
//            //  InputStream stream = getContentResolver().
//            Log.e(Tag, "Starting Http File Sending to URL");
////            URL connectURL = new URL(ShowData.Url() + url);
//            // Open a HTTP connection to the URL
////            HttpURLConnection conn = (HttpURLConnection) connectURL.openConnection();
//
//            // Allow Inputs
////            conn.setDoInput(true);
//
//            // Allow Outputs
////            conn.setDoOutput(true);
////
////            // Don't use a cached copy.
////            conn.setUseCaches(false);
////
////            // Use a post method.
////            conn.setRequestMethod("POST");
////
////            conn.setRequestProperty("Connection", "Keep-Alive");
////
////            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);
//
////            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
//
////            dos.writeBytes(twoHyphens + boundary + lineEnd);
////            dos.writeBytes("Content-Disposition: form-data; name=\"PostId\""+ lineEnd);
////            dos.writeBytes(lineEnd);
////            dos.writeBytes(Id);
////            dos.writeBytes(lineEnd);
////            dos.writeBytes(twoHyphens + boundary + lineEnd);
//
////            dos.writeBytes("Content-Disposition: form-data; name=\"description\""+ lineEnd);
////            dos.writeBytes(lineEnd);
////            dos.writeBytes(Description);
////            dos.writeBytes(lineEnd);
////            dos.writeBytes(twoHyphens + boundary + lineEnd);
//
////            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + iFileName +"\"" + lineEnd);
////            dos.writeBytes(lineEnd);
//
//            Log.e(Tag,"Headers are written");
//
//            // create a buffer of maximum size
//            int bytesAvailable = stream.available();
//
//            int maxBufferSize = 1024;
//            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
//            byte[ ] buffer = new byte[bufferSize];
//
//            // read file and write it into form...
//            int bytesRead = stream.read(buffer, 0, bufferSize);
//
////            while (bytesRead > 0)
////            {
////                dos.write(buffer, 0, bufferSize);
////                bytesAvailable = stream.available();
////                bufferSize = Math.min(bytesAvailable,maxBufferSize);
////                bytesRead = stream.read(buffer, 0,bufferSize);
////            }
////            dos.writeBytes(lineEnd);
////            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
////
////            // close streams
////            stream.close();
////
////            dos.flush();
////
////            Log.e(Tag,"File Sent, Response: "+String.valueOf(conn.getResponseCode()));
////
////            InputStream is = conn.getInputStream();
////
////            // retrieve the response from server
////            int ch;
////
////            StringBuffer b =new StringBuffer();
////            while( ( ch = is.read() ) != -1 ){ b.append( (char)ch ); }
////            String g=b.toString();
////            Log.i("Response",g);
////            dos.close();
////        } catch (MalformedURLException ex) {
//////            activity.runOnUiThread(new Runnable() {
//////                @Override
//////                public void run() {
//////
//////                    Toast.makeText(context, "ارتباط با سرور برقرار نشد", Toast.LENGTH_SHORT).show();
//////                }
//////            });
////        } catch (IOException ioe) {
//////            activity.runOnUiThread(new Runnable() {
//////                @Override
//////                public void run() {
//////
//////                    Toast.makeText(context, "فایل ارسال نشد!جزئیات را چک کنید", Toast.LENGTH_SHORT).show();
////                }
////            });
////
//        }
////        //Pdialog.dismiss();
//        return s;
//    }
}

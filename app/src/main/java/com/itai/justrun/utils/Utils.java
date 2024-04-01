package com.itai.justrun.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.EditText;


import java.util.Map;


public class Utils {

    public interface UserClick{
        void onUserClick(boolean ok);
    }



    public static void showAlert(String title, String message, Context context)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                })
                .show();
    }

    public static void showAlertOkWithCallback(String title, String message, Context context, UserClick callback)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        callback.onUserClick(true);
                        //set what would happen when positive button is clicked
                    }
                })
                .show();
    }


    public static void showAlertConformWithCallback(String title, String message, Context context, UserClick callback)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(context)
//                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        callback.onUserClick(true);
                        //set what would happen when positive button is clicked
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        callback.onUserClick(false);
                        //set what would happen when positive button is clicked
                    }
                })
                .show();
    }


}

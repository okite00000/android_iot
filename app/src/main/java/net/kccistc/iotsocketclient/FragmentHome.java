//http://www.kccistc.net
// IOT : KSH

package net.kccistc.iotsocketclient;

import android.app.Fragment;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;


/**
 * Created by user on 2017-12-27.
 */

public class FragmentHome extends Fragment {
    View view;
    MainActivity mainActivity;
    ImageButton imageButtonLight;
    ImageButton btn;
    boolean lightButtonState = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_home,container,false);
        mainActivity = (MainActivity) getActivity();


        ImageButton data = (ImageButton) view.findViewById(R.id.data);
        data.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.1.100/ondo.html"));

                startActivity(intent);
            }
        });



        imageButtonLight = (ImageButton) view.findViewById(R.id.imageButtonLight);

        imageButtonLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lightButtonState) {
                    mainActivity.sendDataString( "[" + mainActivity.ardId + "]" + "OFF");
                }
                else {
                    mainActivity.sendDataString( "[" + mainActivity.ardId + "]" + "ON");
                }
            }
        });



        colorImageButton();
        return view;



    }




    public void updateImageButton(String strCmd) {
//        Toast.makeText(getActivity(),"TEST: "+strCmd,Toast.LENGTH_SHORT).show();
        if(strCmd.equals("ON")) {
//            Toast.makeText(getActivity(),strCmd,Toast.LENGTH_SHORT).show();
            imageButtonLight.setImageResource(R.drawable.on);
            lightButtonState = true;
            mainActivity.sendDataString( "[" + mainActivity.avrId + "]" + "ON");
        }
        else if(strCmd.equals("OFF")) {
            imageButtonLight.setImageResource(R.drawable.off);
            lightButtonState = false;
            mainActivity.sendDataString( "[" + mainActivity.avrId + "]" + "OFF");
        }

    }
    public void colorImageButton() {
        if(mainActivity.socket != null) {
            imageButtonLight.setBackgroundColor(Color.GREEN);
        } else {
            imageButtonLight.setBackgroundColor(Color.LTGRAY);
        }

    }



}

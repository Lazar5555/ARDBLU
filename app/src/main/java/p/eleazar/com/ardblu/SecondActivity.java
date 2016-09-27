package p.eleazar.com.ardblu;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.UUID;

public class SecondActivity extends ActionBarActivity  {

    ToggleButton tgl1, tgl2, tgl3;
    Button lumn;
    Button btnDes;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;

    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent newint = getIntent();
        address = newint.getStringExtra(ListActivity.EXTRA_ADDRESS);

        setContentView(R.layout.activity_second);
        tgl1 = (ToggleButton) findViewById(R.id.tglOne);
        tgl2 = (ToggleButton) findViewById(R.id.tglTwo);
        tgl3 = (ToggleButton) findViewById(R.id.tglThree);
        btnDes = (Button) findViewById(R.id.btnDesactivar);
        lumn = (Button) findViewById(R.id.tmp);

        new ConnectBT().execute();

        //ToggleButton Uno
        tgl1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    turnOn1();
                }
                else{
                    turnOff1();
                }
            }
        });

        //ToggleButton Dos
        tgl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    turnOn2();
                }
                else{
                    turnOff2();
                }
            }
        });

        //ToggleButton Tres
        tgl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    turnOn3();
                }
                else{
                    turnOff3();
                }
            }
        });

        //Boton desconectar
        btnDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disconnect();
            }
        });

        lumn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                extra();
                rcvtemp();
            }
        });
    }

    private void Disconnect(){
        if (btSocket!=null){
            try {
                btSocket.close();
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
        finish();
    }

    private void turnOn1() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("1".getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void turnOff1() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("2".toString().getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void turnOn2() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("3".toString().getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void turnOff2() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("4".toString().getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void turnOn3() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("5".toString().getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void turnOff3() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("6".toString().getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void extra() {
        if (btSocket != null)
        {
            try {
                btSocket.getOutputStream().write("8".toString().getBytes());
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void rcvtemp(){
        if(btSocket != null){
            try {
                byte[] buffer = new byte[4];
                //String recivido = "";
                //btSocket.getInputStream().;
                btSocket.getInputStream().read(buffer);
                //ByteArrayInputStream input = new ByteArrayInputStream(buffer);
                String recibido = new String(buffer,"UTF-8");
                /*for(int i = 0; i < buffer.length; i++) {
                    recivido += (char)buffer[i];
                }*/
                for(int i = 0; i < 2; i++){
                    //Thread.sleep(1000);
                    Toast.makeText(getApplicationContext(), recibido, Toast.LENGTH_SHORT);
                }
                Toast.makeText(getApplicationContext(), recibido +" Grados", Toast.LENGTH_SHORT).show();
            }
            catch (IOException e) {
                Toast.makeText(getApplicationContext(), R.string.error, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_second, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings_second) {
            Intent intent = new Intent(SecondActivity.this, Information.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>{
        private boolean ConnectSuccess = true;
        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(SecondActivity.this, "Conectando...", "Espera Por Favor");
        }

        @Override
        protected Void doInBackground(Void... devices) {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();
                }
            }
            catch (IOException e) {
                ConnectSuccess = false;
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                Toast.makeText(getApplicationContext(),R.string.failConn ,Toast.LENGTH_LONG).show();
                finish();
            }
            else {

                Toast.makeText(getApplicationContext(),R.string.sucessConn ,Toast.LENGTH_LONG).show();
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }

}


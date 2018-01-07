/*
    AddOrEditServerActivity.java - activity of Simple Wake On Lan application, that's allow to add or edit wake on lan destination.

    Copyright (C) 2018  Maxim Belyaev(NFS_MONSTR)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package tk.nfsmonstr.simplewakeonlan;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class AddOrEditServerActivity extends AppCompatActivity {
    int mode = 0;
    int number = 0;
    int countOfServers = 0;
    String ip = "", mac = "", name = "";
    int port = 9;
    boolean broadcast = false;
    SharedPreferences sharedPreferences = null;
    Activity mActivity = this;
    EditText nameEdit,ipEdit,portEdit,macEdit;
    CheckBox broadcastCheckbox;

    private boolean allFieldsAreCorrect() {
        String name = nameEdit.getText().toString();
        String ip = ipEdit.getText().toString();
        String mac = macEdit.getText().toString();
        int port = Integer.valueOf(portEdit.getText().toString());
        return (name.length()>0) && (port > 0) && (port < 65536) && (Checker.checkIp(ip)) && (Checker.checkMac(mac));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_server);

        sharedPreferences = getSharedPreferences(getString(R.string.settings), Context.MODE_PRIVATE);
        countOfServers = sharedPreferences.getInt("serversCount",0);
        nameEdit = findViewById(R.id.name_edit);
        ipEdit = findViewById(R.id.ip_edit);
        portEdit = findViewById(R.id.port_edit);
        portEdit.setText(String.valueOf(port));
        macEdit = findViewById(R.id.mac_edit);
        broadcastCheckbox = findViewById(R.id.broadcastCheckBox);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mode = extras.getInt("EXTRA_MODE", 0);
            if (mode == 1) {
                number = extras.getInt("EXTRA_NUMBER", 0);
                if (countOfServers>0) {
                    name = sharedPreferences.getString("server-name-".concat(String.valueOf(number)),"");
                    ip = sharedPreferences.getString("server-ip-".concat(String.valueOf(number)),"");
                    mac = sharedPreferences.getString("server-mac-".concat(String.valueOf(number)),"");
                    port = sharedPreferences.getInt("server-port-".concat(String.valueOf(number)),0);
                    broadcast = sharedPreferences.getBoolean("server-broad-".concat(String.valueOf(number)),false);
                    nameEdit.setText(name);
                    ipEdit.setText(ip);
                    if (port==0)
                        port = 9;
                    portEdit.setText(String.valueOf(port));
                    macEdit.setText(mac);
                    broadcastCheckbox.setChecked(broadcast);
                }
            }
        }

        Button saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (allFieldsAreCorrect()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    if (mode==0) {
                        number = countOfServers;
                        countOfServers++;
                    }
                    editor.putString("server-name-".concat(String.valueOf(number)),nameEdit.getText().toString());
                    editor.putString("server-ip-".concat(String.valueOf(number)),ipEdit.getText().toString());
                    editor.putString("server-mac-".concat(String.valueOf(number)),macEdit.getText().toString());
                    editor.putInt("server-port-".concat(String.valueOf(number)),Integer.valueOf(portEdit.getText().toString()));
                    editor.putBoolean("server-broad-".concat(String.valueOf(number)),broadcastCheckbox.isChecked());
                    editor.putInt("serversCount",countOfServers);
                    editor.apply();
                    finish();
                } else {
                    Toast.makeText(mActivity,getString(R.string.incorrect_input),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}

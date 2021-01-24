/*
*       Copyright 2020. Huawei Technologies Co., Ltd. All rights reserved.

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
*/
package com.example.testinglocationkit.location.fusedlocation;

import com.example.testinglocationkit.R;
import com.example.logger.LocationLog;
import com.example.logger.LogFragment;
import com.example.logger.LoggerActivity;

import android.app.FragmentTransaction;
import android.graphics.Color;
import android.util.Log;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

/**
 * add addLogFragment() method, if you want to show log on the screen
 *
 * @since 2020-5-11
 */
public class LocationBaseActivity extends LoggerActivity {
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * print log on the screen
     */
    protected void addLogFragment() {
        final FragmentTransaction transaction = getFragmentManager().beginTransaction();
        final LogFragment fragment = new LogFragment();
        if(transaction != null) {
            transaction.replace(R.id.framelog, fragment);
            transaction.commit();
        }else {
            LocationLog.e("LocationBaseActivity", "addLogFragment error !");
        }
    }

    public void initDataDisplayView(String TAG,TableLayout tableLayout, String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = jsonObject.getString(key);

                TableRow tableRow = new TableRow(getBaseContext());

                TextView textView = new TextView(getBaseContext());
                textView.setText(key);
                textView.setTextColor(Color.GRAY);
                textView.setId(getBaseContext().getResources()
                        .getIdentifier(key + "_key", "id", getBaseContext().getPackageName()));
                tableRow.addView(textView);

                EditText editText = new EditText(getBaseContext());
                editText.setText(value);
                editText.setId(getBaseContext().getResources()
                        .getIdentifier(key + "_value", "id", getBaseContext().getPackageName()));
                editText.setTextColor(Color.DKGRAY);
                tableRow.addView(editText);
                tableLayout.addView(tableRow);
            }
        } catch (JSONException e) {
            Log.e(TAG, "initDataDisplayView JSONException:" + e.getMessage());
        }
    }
}

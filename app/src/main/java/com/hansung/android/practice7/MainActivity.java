package com.hansung.android.practice7;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import static com.hansung.android.practice7.R.id.result;

public class MainActivity extends AppCompatActivity {

    EditText input;
    TextView result;
 // public static final String
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = (EditText) findViewById(R.id.edit_title);
        result=(TextView) findViewById(R.id.result);

        Button saveBtn = (Button) findViewById(R.id.saveBtn);
        Button loadBtn = (Button) findViewById(R.id.loadBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
              saveToInternalStorage();
            }
        });
        loadBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                loadFromInternalStorage();
            }
        });
    }

  private void saveToInternalStorage() {
          String data = input.getText().toString();


             try {
                    FileOutputStream fos = openFileOutput ("myfile.txt",
                                               Context.MODE_APPEND);// 저장모드
                    PrintWriter out = new PrintWriter(fos);
                      out.println(data);
                       out.close();

                       result.setText("file saved");
                  } catch (Exception e) {
                      result.setText("Exception: internal file writing");
                  }
        }
    private void loadFromInternalStorage() {
           try {
                     FileInputStream fis = openFileInput("myfile.txt");//파일명
                     BufferedReader buffer = new BufferedReader
                            (new InputStreamReader(fis));
                      String str = buffer.readLine(); // 파일에서 한줄을 읽어옴



                       StringBuffer data = new StringBuffer();
                     while (str != null) {
                              data.append(str + "\n");
                                 str = buffer.readLine();
                           }
                        buffer.close();
                     result.setText(data);
                 } catch (FileNotFoundException e) {
                        result.setText("File Not Found");
                    } catch (Exception e) {
                       result.setText("Exception: internal file reading");
              }
           }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        //


        menu.findItem(R.id.internal_storage).setChecked(true);

        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.internal_storage:
       //선택정보 저장

                break;

            case R.id.external_storage:


                break;
        }



        return super.onOptionsItemSelected(item);
    }
}

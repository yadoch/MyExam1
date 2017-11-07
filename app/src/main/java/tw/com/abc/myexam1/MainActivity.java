package tw.com.abc.myexam1;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private View add, del, show;
    private EditText todo, date;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todo = (EditText)findViewById(R.id.todo);
        date = (EditText)findViewById(R.id.date);

        add = findViewById(R.id.add);
        del = findViewById(R.id.del);
        show = findViewById(R.id.show);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                del();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        sp=getSharedPreferences("data",MODE_PRIVATE);
        editor=sp.edit();

    }

    private void show() {
        Map<String,String> data= (Map<String, String>) sp.getAll();
        Set<String> todos =data.keySet();
        String[] allTodo = new String[todos.size()];
        int i=0;
        for (String todo: todos){
            allTodo[i]  =todo + "\n"+ sp.getString(todo,"");
            i++;
        }
        AlertDialog dialog = null;
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("ToDo List ");
        builder.setMessage("Todo List below!!");
        builder.setItems(allTodo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog=builder.create();


        dialog.show();
/*
       // 換行 \n 在Log.i中也會生效，造成看不到後續內容
       Log.i("geoff","todo:"+allTodo[0]);
*/

    }


    private void del() {
        String todoString = todo.getText().toString();
        editor.remove(todoString);
        Toast.makeText(this,"del"+todoString,Toast.LENGTH_SHORT).show();

    }

    private void add(){
        String todoString = todo.getText().toString();
        String dateString = date.getText().toString();
        if (todoString.equals("")){
            Toast.makeText(this, "empty", Toast.LENGTH_SHORT).show();
            return;

        }
        editor.putString(todoString,dateString );
        editor.commit();

    }

}

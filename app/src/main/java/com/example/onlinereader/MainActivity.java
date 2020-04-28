package com.example.onlinereader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import com.bifan.txtreaderlib.ui.HwTxtPlayActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private PopupMenu popupMenu;
    private Intent intent_find,intent_import;
    private List<Book> bookList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化Intent
        intent_find = new Intent(MainActivity.this,Reader_Find.class);
        intent_import = new Intent(MainActivity.this,Reader_Import.class);

        initview();

        //按钮设置弹出菜单
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showPopupMenu(button);

            }

        });

        initBook();
//列表
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.book_list);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter = new BookAdapter(bookList);

        recyclerView.setAdapter(adapter);





    }

    private void initBook() {
        for (int i = 1; i < 7; i++) {
            Book book1 = new Book("book1", R.drawable.book1);
            bookList.add(book1);

            Book book2 = new Book("book2", R.drawable.book2);
            bookList.add(book2);
            Book book3 = new Book("book3", R.drawable.book3);
            bookList.add(book3);
            Book book4 = new Book("book4", R.drawable.book4);
            bookList.add(book4);
            Book book5 = new Book("book5", R.drawable.book5);
            bookList.add(book5);
            Book book6 = new Book("book6", R.drawable.book6);
            bookList.add(book6);


        }
    }

    //初始化id
    private void initview() {
        button = findViewById(R.id.button);
    }


    private void showPopupMenu(View view) {
        // 这里的view代表popupMenu需要依附的view
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, view);
        // 获取布局文件
        popupMenu.getMenuInflater().inflate(R.menu.book_menu, popupMenu.getMenu());
        popupMenu.show();
        // 通过上面这几行代码，就可以把控件显示出来了
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.book_find :
                        startActivity(intent_find);
                        break;
                    case R.id.book_import :
                        //startActivity(intent_import);
                        //startActivity(new Intent(MainActivity.this,Book_Read.class));
                        startActivity(new Intent(MainActivity.this,RvOneActivity.class));
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popupMenu.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

                // makeText(MainActivity.this, "vectory", LENGTH_SHORT).show();
                // 控件消失时的事件
            }
        });

    }


}

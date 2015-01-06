//all activities extend this activity
//mainly so the menu is the same

package com.squigg1ers.showertimer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class BaseActivity extends ActionBarActivity {
    protected DbHelper dbhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbhelper = new DbHelper(this);
    }

    //inflate the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    //menu selection opens a new activity for the item selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;
        int id = item.getItemId();
/*
        if (id == R.id.AddTimerActivity) {
            onClickMenuAddTimer(item);
        }
        else
            handled = super.onOptionsItemSelected(item);
*/
        return handled;
    }
}

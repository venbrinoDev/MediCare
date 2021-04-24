package com.victor.victor.Main.searchDocotrs;

import android.text.Editable;
import android.text.TextWatcher;

 public abstract class SearchImplement  implements TextWatcher {


    
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        textChange(charSequence,i,i1,i2);
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    protected abstract void textChange(CharSequence charSequence, int i, int i1, int i2);
}

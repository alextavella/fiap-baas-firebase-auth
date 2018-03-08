package com.alextavella.firebasedemo.extensions

import android.support.design.widget.TextInputLayout

/**
 * Created by alextavella on 07/03/2018.
 */

fun TextInputLayout.getText(): String
{
    return this.editText?.text.toString()
}
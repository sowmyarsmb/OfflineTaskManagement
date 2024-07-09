package com.example.offlinetasktracker.util

import android.content.Intent
import android.os.Parcelable

fun Intent.putParcelableExtra(name: String, value: Parcelable): Intent {
    return this.apply { putExtra(name, value) }
}

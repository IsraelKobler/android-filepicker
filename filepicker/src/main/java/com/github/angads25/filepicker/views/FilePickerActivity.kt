/*
 * Copyright (C) 2018 Angad Singh
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.angads25.filepicker.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.angads25.filepicker.R
import kotlinx.android.synthetic.main.activity_filepicker.*

/**
 * Created by Angad Singh on 30/11/18.
 */
class FilePickerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filepicker)

        val filePickerFragment = FilePickerFragment()
        setSupportActionBar(toolbar)
        filePickerFragment.setSupportActionBar(toolbar)
        supportFragmentManager.beginTransaction().add(R.id.content_main, FilePickerFragment()).commit()
    }
}
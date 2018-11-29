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
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.github.angads25.filepicker.R
import com.github.angads25.filepicker.adapter.FileListAdapter
import com.github.angads25.filepicker.interfaces.Pickable
import com.github.angads25.filepicker.model.FileItem
import kotlinx.android.synthetic.main.fragment_filepicker.view.*

/**<p>
 * Created by Angad Singh on 15/11/18.
 * </p>
 */

class FilePickerFragment: Fragment(), Pickable<FileItem> {

    private var toolbar: Toolbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_filepicker, container, false)

        toolbar?.title = "Directory"
        toolbar?.subtitle = "Directory Path"

        val fileListAdapter = FileListAdapter(requireContext())
        val fileItem1 = FileItem()
        fileItem1.type = 0
        fileItem1.fileName = "Singh"
        fileItem1.description = "Description"
        fileItem1.isSelected = false
        fileItem1.isSelectable = false
        fileItem1.path = "/sdasd/asd/asdasdsd/"

        val fileItem2 = FileItem()
        fileItem2.type = 0
        fileItem2.fileName = "Angad"
        fileItem2.description = "Description"
        fileItem2.isSelected = false
        fileItem2.isSelectable = false
        fileItem2.path = "/sdasd/asd/asddsafasd/"

        val fileItem3 = FileItem()
        fileItem3.type = 1
        fileItem3.fileName = "Directory 1"
        fileItem3.description = "Description"
        fileItem3.isSelected = false
        fileItem3.isSelectable = true
        fileItem3.path = "/sdasd/asd/asdasfad/"

        val fileItem4 = FileItem()
        fileItem4.type = 1
        fileItem4.fileName = "Directory 2"
        fileItem4.description = "Description"
        fileItem4.isSelected = false
        fileItem4.isSelectable = true
        fileItem4.path = "/sdasd/asd/asdgfdgasd/"

        val fileItem5 = FileItem()
        fileItem5.type = 1
        fileItem5.fileName = "Directory 3"
        fileItem5.description = "Description"
        fileItem5.isSelected = false
        fileItem5.isSelectable = true
        fileItem5.path = "/sdasd/asd/asdaertsd/"

        val fileItem6 = FileItem()
        fileItem6.type = 1
        fileItem6.fileName = "Directory 4"
        fileItem6.description = "Description"
        fileItem6.isSelected = false
        fileItem6.isSelectable = true
        fileItem6.path = "/sdasd/asd/asdasqwed/"

        val arrayList = ArrayList<FileItem>()
        arrayList.add(fileItem1)
        arrayList.add(fileItem2)
        arrayList.add(fileItem3)
        arrayList.add(fileItem4)
        arrayList.add(fileItem5)
        arrayList.add(fileItem6)
        fileListAdapter.addAllItems(arrayList)

        view.fileList.adapter = fileListAdapter
        return view
    }

    fun setSupportActionBar(toolbar: Toolbar) {
        this.toolbar = toolbar
    }

    override fun onSelectionChanged(@NonNull pickable: FileItem, state: Boolean) {
        Log.e("FileName", pickable.fileName)
    }
}
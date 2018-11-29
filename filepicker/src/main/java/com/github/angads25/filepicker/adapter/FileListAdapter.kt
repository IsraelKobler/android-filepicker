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

package com.github.angads25.filepicker.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.github.angads25.filepicker.R
import com.github.angads25.filepicker.interfaces.Pickable
import com.github.angads25.filepicker.model.FileItem
import kotlinx.android.synthetic.main.list_item_file.view.*

/**
 * <p>
 * Created by Angad Singh on 15/11/18.
 * </p>
 */

class FileListAdapter(private val context: Context):
        RecyclerView.Adapter<FileListAdapter.FileListViewHolder>(),
        CompoundButton.OnCheckedChangeListener {
    private val fileList = ArrayList<FileItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_file, parent, false)
        return FileListViewHolder(view)
    }

    override fun getItemCount(): Int = fileList.size

    override fun onBindViewHolder(holder: FileListViewHolder, position: Int) {
        val fileItem = fileList[position]
        holder.labelDesc.text = fileItem.description
        holder.icFileType.setImageResource(when (fileItem.type) {
            0 -> R.mipmap.ic_type_file
            1 -> R.mipmap.ic_type_folder
            else -> R.mipmap.ic_type_folder
        })
        holder.labelFileName.text = fileItem.fileName
        if (fileItem.isSelectable) {
            holder.selectedCheckBox.tag = fileItem
            holder.selectedCheckBox.setOnCheckedChangeListener(null)
            holder.selectedCheckBox.isChecked = fileItem.isSelected
            holder.selectedCheckBox.setOnCheckedChangeListener(this)
        } else {
            holder.selectedCheckBox.setOnCheckedChangeListener(null)
            holder.selectedCheckBox.visibility = View.GONE
        }
    }

    private fun addItemToList(fileItem: FileItem) {
        if (!fileList.contains(fileItem)) {
            fileList.add(fileItem)
            notifyItemInserted(fileList.size)
        }
    }

    fun addAllItems(fileItems: ArrayList<FileItem>) {
        fileItems.sort()
        for(fileItem in fileItems) {
            addItemToList(fileItem)
        }
    }

    fun clear() {
        val size = fileList.size
        fileList.clear()
        notifyItemRangeRemoved(0, size)
    }

    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        val fileItem = buttonView?.tag as FileItem
        fileItem.isSelected = isChecked
        if(context is Pickable<*>) {
            (context as Pickable<FileItem>).onSelectionChanged(fileItem, isChecked)
        }
    }

    class FileListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val labelDesc: AppCompatTextView = view.labelDesc
        val icFileType: AppCompatImageView = view.icFileType
        val labelFileName: AppCompatTextView = view.labelFileName
        val selectedCheckBox: AppCompatCheckBox = view.selectedCheckBox
    }
}
package com.example.tse.news.ui


import android.app.Dialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.LiveData
import com.example.tse.news.Injection
import com.example.tse.news.MainAdapter

import com.example.tse.news.R
import com.example.tse.news.model.Article
import com.example.tse.news.model.Source
import kotlinx.android.synthetic.main.fragment_list_news.*
import java.lang.StringBuilder

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class ListNewsFragment : Fragment() {

    private lateinit var viewModel: ListNewsViewModel
    private lateinit var viewModelSource: ListSourcesNewsViewModel
    private val adapter = ArticleAdapter()
    private val TAG: String = ListNewsFragment::class.java.simpleName
    private var lista: List<Source> = emptyList()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


//        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(view!!.context))
//                .get(ListNewsViewModel::class.java)
//
        viewModelSource = ViewModelProviders.of(this, Injection.provideViewModelFactorySourcesNews(view!!.context))
                .get(ListSourcesNewsViewModel::class.java)
//
//
//
        viewModelSource.sources.observe(this, Observer<List<Source>> {

            lista = it

            val csSources: MutableList<CharSequence> = ArrayList()
            for (i in lista.indices) {
                csSources.add(lista[i].name)
            }

            val csListSources: Array<CharSequence> = csSources.toTypedArray()

            // Listener Button show Dialog select Sources news.
            btnSources.setOnClickListener {

                // Initialize values from Sources News items selected
                val itemSelected = ArrayList<Int>()

                // Create Dialog from List Sources News
                val dialog = MaterialAlertDialogBuilder(view!!.context)
                        .setTitle("Sources news")
                        .setMultiChoiceItems(csListSources, null) { dialog, which, isChecked ->
                            run {
                                // If the user checked the item, add it to the selectedItem
                                when {
                                    isChecked -> itemSelected.add(which)
                                    itemSelected.contains(which) -> itemSelected.remove(which)
                                    else -> Unit
                                }
                            }
                        }
                        .setPositiveButton("Ok") { _, _ ->
                            run {
                                //var builder = StringBuilder()
                                if ((itemSelected.size != 0) && (itemSelected.size < 20)) {
                                   // for (i in itemSelected) {
                                   //     var source: String = c[i].toString()
                                   //     builder = builder.append(source + ",")
                                  //  }
                                  //  Log.e(TAG, "Lista de fuentes: " + builder)

                                    Toast.makeText(view?.context, "Tus datos se guardaran", Toast.LENGTH_SHORT).show()

                                } else {
                                    Toast.makeText(view?.context, "Solo puedes elegir hasta 20 fuentes de informacion", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                        .setNegativeButton("Cancel") { _, _ -> Log.e(TAG, "Negativo") }
                        .setCancelable(false)
                        .create()
                dialog.show()
                dialog.listView.isVerticalScrollBarEnabled = false

                val btn: Button = dialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                val param: ViewGroup.MarginLayoutParams = btn.layoutParams as ViewGroup.MarginLayoutParams
                param.rightMargin = 8
                btn.layoutParams = param


/*
            val builder = AlertDialog.Builder(view!!.context,R.style.MyDialogThme)
            builder.setTitle("Sources")
                    .setMultiChoiceItems(c, null) { dialog, which, isChecked -> run {
                        if (isChecked){
                            // If the user checked the item, add it to the selectedItem
                            itemSelect.add(which)
                            Log.e(TAG, "Seleccion: " + c[which])
                        } else if(itemSelect.contains(which)){
                            itemSelect.remove(which)
                        } else Unit
                    } }
                    //.setAdapter(MyCustomAdapter(view!!.context, lista), null)
                    // Add Buttons
                    .setPositiveButton("Ok") { _, _ ->
                        run {
                            //var builder = StringBuilder()
                            if ((itemSelect.size != 0) && (itemSelect.size < 20) ){
                                /*for (i in itemSelect){
                                    var source :String = c[i].toString()
                                    builder = builder.append(source + ",")
                                }
                                Log.e(TAG, "Lista de fuentes: " + builder)*/
                                Toast.makeText(view?.context, "Tus datos se guardaran", Toast.LENGTH_SHORT).show()

                            }else {
                                Toast.makeText(view?.context, "Solo puedes elegir hasta 20 fuentes de informacion", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    .setNegativeButton("Cancel"){_, _ -> Log.e(TAG, "Negativo")}
                    .setCancelable(false)
            val dialog: AlertDialog = builder.create()

               // dialog.listView.isVerticalScrollBarEnabled = false
               // dialog.listView.choiceMode = ListView.CHOICE_MODE_MULTIPLE
               /* dialog.listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->

                }*/

            dialog.show()
                // establecer alto y ancho de ventana de dialogo
               // dialog.window.setLayout(600,800)

                */

            }


        })

        viewModelSource.networErrors.observe(this, Observer<String> {
            Toast.makeText(view?.context, "Woooss ${it}", Toast.LENGTH_LONG).show()
        })
//
//        initAdapter()
//        viewModel.articles


//


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_list_news, container, false)


        return view

    }


    private fun initAdapter() {
        list.adapter = adapter
        viewModel.articles.observe(this, Observer<PagedList<Article>> {
            Log.e(TAG, "List: ${it?.size}")
            showEmptyList(it?.size == 0)
            adapter.submitList(it)
        })

        viewModel.networErrors.observe(this, Observer<String> {
            Toast.makeText(view?.context, "\uD83D\uDE28 Wooops ${it}", Toast.LENGTH_LONG).show()
        })

    }

    private fun showEmptyList(show: Boolean) {
        if (show) {
            emptyList.visibility = View.VISIBLE
            list.visibility = View.GONE
        } else {
            emptyList.visibility = View.GONE
            list.visibility = View.VISIBLE
        }
    }


    private fun setUpScrollListener() {
        val layoutManager = list.layoutManager as LinearLayoutManager

    }

    private class MyCustomAdapter(context: Context, dataSources: List<Source>) : BaseAdapter() {

        private val mContext: Context
        private val ds: List<Source>


        init {
            mContext = context
            ds = dataSources
        }

        override fun getViewTypeCount(): Int {
            return count
        }

        override fun getItemViewType(position: Int): Int {
            return position
        }

        override fun getCount(): Int {
            return ds.size
        }

        override fun getItem(position: Int): Any {
            return ds[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
            var convertView = convertView
            val holder: ViewHolder

            if (convertView == null) {
                val layoutInflater = LayoutInflater.from(mContext)
                convertView = layoutInflater.inflate(R.layout.row, parent, false)
                holder = ViewHolder(convertView)
                convertView.tag = holder
            } else {
                holder = convertView.tag as ViewHolder
            }

            holder.checkBox!!.isChecked = ds[position].isChecked
            holder.radioSource!!.isChecked = ds[position].isChecked

            holder.checkBox!!.text = ds[position].isChecked.toString()
            holder.nameSource!!.text = ds[position].name + ds[position].isChecked

            holder.checkBox!!.setTag(R.integer.btnplusview, convertView)
            holder.radioSource!!.setTag(R.integer.btnplusview, convertView)

            holder.checkBox!!.tag = position
            holder.radioSource!!.tag = position

            holder.radioSource!!.setOnClickListener {
                val posti = holder.radioSource!!.tag as Int
                if (ds[posti].isChecked) {
                    ds[posti].isChecked = false
                    holder.radioSource.isChecked = false
                    public_modelArrayList = ds as ArrayList<Source>
                } else {
                    ds[posti].isChecked = true
                    holder.radioSource.isChecked = true
                    public_modelArrayList = ds as ArrayList<Source>
                }
            }

//            holder.checkBox!!.setOnClickListener{
//                val tempview = holder.checkBox!!.getTag(R.integer.btnplusview) as View
//                val pos = holder.checkBox!!.tag as Int
//
//                Log.e("CLICK CHECK", "VALOR POS: $pos")
//                Toast.makeText(mContext, "Checkbox $pos clicked", Toast.LENGTH_SHORT).show()
//                if (ds[pos].isChecked){
//                    Log.e("clic", "if: aca")
//                    ds[pos].isChecked = false
//                    holder.checkBox!!.isChecked = false
//                    public_modelArrayList = ds as ArrayList<Source>
//                }else {
//                    Log.e("clic", "if no: aca")
//                    ds[pos].isChecked = true
//                    holder.checkBox!!.isChecked = true
//                    public_modelArrayList = ds as ArrayList<Source>
//                }
//            }


            return convertView
//            val layoutInflater = LayoutInflater.from(mContext)
//            val row_source = layoutInflater.inflate(R.layout.row, viewGroup, false )
//            val nameSource = row_source.findViewById<CheckBox>(R.id.chBoxSource)
//            val positionT = row_source.findViewById<TextView>(R.id.txtRow)
//
//            nameSource.tag = position
//            positionT.text = ds[position].name
//
//            return row_source
        }


    }


    private class ViewHolder(row: View?) {
        var checkBox: CheckBox
        var nameSource: TextView
        var radioSource: RadioButton

        init {
            this.checkBox = row?.findViewById(R.id.chBoxSource) as CheckBox
            this.nameSource = row?.findViewById(R.id.txtRow) as TextView
            this.radioSource = row?.findViewById(R.id.rbSource) as RadioButton
        }
    }

    companion object {
        lateinit var public_modelArrayList: ArrayList<Source>
    }


}



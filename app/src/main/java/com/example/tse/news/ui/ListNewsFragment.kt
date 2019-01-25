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
import com.example.tse.news.model.SourceUser
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
    private lateinit var viewModelSourceUser: ListSourceUserViewModel
    private val adapter = ArticleAdapter()
    private val TAG: String = ListNewsFragment::class.java.simpleName
    private var lista: List<Source> = emptyList()


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        // SourceUser ViewModel assign
        viewModelSourceUser = ViewModelProviders.of(this, Injection.provideViewModelFactorySourceUser(view!!.context))
                .get(ListSourceUserViewModel::class.java)

        // SourcesUser ViewModel add observer to "observer" change
        viewModelSourceUser.sourcesUser.observe(this, Observer<List<SourceUser>> {
            Log.e(TAG, "Fuentes del Usuario elegidas : ${it.size}")
            var builder = StringBuilder()
             for (i in it) {
                 var source: String = i.name
                 builder = builder.append(source + ",")
              }
              Log.e(TAG, "Lista de fuentes builder: " + builder)
        })

//        viewModel = ViewModelProviders.of(this, Injection.provideViewModelFactory(view!!.context))
//                .get(ListNewsViewModel::class.java)
//

        // Sources ViewModel assign
        viewModelSource = ViewModelProviders.of(this, Injection.provideViewModelFactorySourcesNews(view!!.context))
                .get(ListSourcesNewsViewModel::class.java)
//
//
//
        // Sources ViewModel add observer change
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

                                val userSources = ArrayList<SourceUser>()

                                if ((itemSelected.size != 0) && (itemSelected.size < 20)) {

                                    // Get Sources from user selected
                                    for (i in itemSelected) {
                                        // Create a object SourceUser
                                        val sourceUser = SourceUser(lista[i].id, lista[i].name, lista[i].description, lista[i].url, lista[i].category, lista[i].language, lista[i].country)
                                        // Add sourceUser to ArrayList
                                        userSources.add(sourceUser)
                                    }

                                    // Add userSources to viewModelSourceUser.insertSourceUser
                                    viewModelSourceUser.insertSourceUser(userSources)

                                    Log.e(TAG, "Lista tamano ${userSources.size}")


                                    Toast.makeText(view?.context, "Tus datos se guardaran  ${userSources.size}", Toast.LENGTH_SHORT).show()

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

            }


        })

        viewModelSource.networErrors.observe(this, Observer<String> {
            Toast.makeText(view?.context, "Woooss ${it}", Toast.LENGTH_LONG).show()
        })
//
//        initAdapter()
//        viewModel.articles

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


}



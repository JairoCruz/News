package com.example.tse.news.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tse.news.R
import org.w3c.dom.Text


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class DetailNewsFragment : Fragment() {



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_news, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById(R.id.txtTitleDetail) as TextView
        val image = view.findViewById<ImageView>(R.id.imageDetailTitle)
        val author = view.findViewById<TextView>(R.id.txtAuthorDetail)
        val description = view.findViewById<TextView>(R.id.txtDescriptionDetail)
        val publishedAt = view.findViewById<TextView>(R.id.txtPublishedAtDetail)
        //view.findViewById<TextView>(R.id.txtShow).text = title

        title.text = arguments?.getString("title")
        Glide.with(view.context).load(arguments?.getString("urlToImage")).into(image)
        //if(article.author.isNullOrEmpty()) author.text = "Unknow" else author.text = article.author
        if (arguments?.getString("author").isNullOrEmpty()) author.text = "By unknow" else author.text = "By " + arguments?.getString("author")
        description.text = arguments?.getString("description")
        publishedAt.text = arguments?.getString("publishedAt")

    }
}

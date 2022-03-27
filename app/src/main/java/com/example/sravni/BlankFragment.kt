package com.example.sravni

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.Html.FROM_HTML_MODE_LEGACY
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.example.sravni.databinding.FragmentBlankBinding
import com.example.sravni.ui.main.FactorAPI
import com.example.sravni.ui.main.Factors
import com.example.sravni.ui.main.RetrofitHelper
import com.example.sravni.ui.main.Showbottom
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class BlankFragment : Fragment() {
    private var _binding : FragmentBlankBinding? = null
    private val binding get() = _binding!!
    private lateinit var showbottom: Showbottom
    private var linearVisible = "linearlayoutvisible"
    private var imageID = "imageID"
    var api = RetrofitHelper.getInstance().create(FactorAPI::class.java)

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(linearVisible, binding.lin.isVisible)
//        outState.putInt(imageID, binding.imageView4.resources.)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBlankBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textView17.text = Html.fromHtml(getString(R.string.welcome_messages,
            "БТ","КМ","КТ","КБМ","КО","КВС"), FROM_HTML_MODE_LEGACY)
//        api.everything().enqueue(object : Callback<Factors>{
//            override fun onResponse(call: Call<Factors>, response: Response<Factors>) {
////                println(response.body())
//            }
//
//            override fun onFailure(call: Call<Factors>, t: Throwable) {
//                println(t.message)
//            }
//
//        })
//        api.everythingRX()
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnNext {x->println(Thread.currentThread().name)}
//            .subscribeOn(Schedulers.io())
//            .subscribe({response -> println(Thread.currentThread().name)}, {t -> println(t) })
//        GlobalScope.launch {
//            var list: Factors = api.everythingCourutines()
//            println(list.factors)
//        }
        GlobalScope.launch {
        flow<Factors> {
            emit(api.everythingCourutines())
        }.collect { println(it.factors) }
        }
        binding.constr.setOnClickListener {
            hideshow()
        }
        showbottom = Showbottom()
        binding.city.setOnClickListener {
            showbottom.onClick(it)
        }
        binding.horsepowers.setOnClickListener {
            showbottom.onClick(it)
        }
        binding.drivers.setOnClickListener {
            showbottom.onClick(it)
        }
        binding.age.setOnClickListener {
            showbottom.onClick(it)
        }
        binding.minimum.setOnClickListener {
            showbottom.onClick(it)
        }
        binding.incidentless.setOnClickListener {
            showbottom.onClick(it)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if (savedInstanceState!=null){
            binding.lin.isVisible = savedInstanceState.getBoolean(linearVisible, false)
            if (binding.lin.isVisible == true){
                binding.imageView4.setImageResource(R.drawable.img_3)
            }
            else
                binding.imageView4.setImageResource(R.drawable.img_2)
        }
    }
    fun hideshow(){
        var animation = AnimationUtils.loadAnimation(context, R.anim.alpha)
        if (binding.lin.visibility == View.GONE){
            binding.lin.visibility = View.VISIBLE
            binding.imageView4.setImageResource(R.drawable.img_3)
            binding.lin.startAnimation(animation)
        }
        else{
            animation = AnimationUtils.loadAnimation(context, R.anim.alpha1)
            binding.lin.visibility = View.GONE
            binding.imageView4.setImageResource(R.drawable.img_2)
            binding.lin.startAnimation(animation)
        }
    }

    companion object {
        fun newInstance() = BlankFragment()
    }
}
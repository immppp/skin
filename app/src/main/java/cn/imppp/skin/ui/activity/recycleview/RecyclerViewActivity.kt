package cn.imppp.skin.ui.activity.recycleview

import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import cn.imppp.multitype_adapter_library.MultiTypeAdapter
import cn.imppp.multitype_adapter_library.binder.MultiTypeBinder
import cn.imppp.multitype_adapter_library.callback.OnViewClickListener
import cn.imppp.multitype_adapter_library.createMultiTypeAdapter
import cn.imppp.skin.R
import cn.imppp.skin.adapter.binder.*
import cn.imppp.skin.base.BaseActivity
import cn.imppp.skin.databinding.ActivityRecyclerviewBinding
import kotlinx.android.synthetic.main.activity_recyclerview.*

class RecyclerViewActivity : BaseActivity<RecyclerViewModel, ActivityRecyclerviewBinding>(),
    OnViewClickListener {

    private lateinit var mMultiTypeAdapter: MultiTypeAdapter

    override fun viewModelClass(): Class<RecyclerViewModel> {
        return RecyclerViewModel::class.java
    }

    override fun layoutRes(): Int {
        return R.layout.activity_recyclerview
    }

    override fun loadData() {
        mMultiTypeAdapter = createMultiTypeAdapter(recyclerView, LinearLayoutManager(this))

        mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
            add(TopBinder().apply {
                setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
            })

            add(
                CategoryContainerBinder(
                    listOf(
                        "男装",
                        "女装",
                        "鞋靴",
                        "内衣内饰",
                        "箱包",
                        "美妆护肤",
                        "洗护",
                        "腕表珠宝",
                        "手机",
                        "数码"
                    ).map {
                        CategoryItemBinder(it).apply {
                            setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                        }
                    })
            )

            add(RecommendContainerBinder((1..8).map {
                RecommendGoodsBinder().apply {
                    setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                }
            }))

            add(HorizontalScrollBinder((0..11).map {
                HorizontalItemBinder("$it").apply {
                    setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                }
            }))

            add(GoodsGridContainerBinder((1..20).map {
                GoodsBinder("$it").apply {
                    Log.i("goodBinder", "goodData click : onRecycleViewClick")
                    setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                }
            }))
        })
    }

    override fun onRecycleViewClick(view: View, any: Any?) {
        when (view.id) {
            R.id.good_container -> {
                any as GoodsBinder
                mMultiTypeAdapter.notifyAdapterChanged(mutableListOf<MultiTypeBinder<*>>().apply {
                    add(TopBinder().apply {
                        setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                    })

                    add(
                        CategoryContainerBinder(
                            listOf(
                                "男装",
                                "女装",
                                "鞋靴",
                                "内衣内饰",
                                "箱包",
                                "美妆护肤",
                                "洗护",
                                "腕表珠宝",
                                "手机",
                                "数码"
                            ).map {
                                CategoryItemBinder(it).apply {
                                    setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                                }
                            })
                    )

                    add(RecommendContainerBinder((1..8).map {
                        RecommendGoodsBinder().apply {
                            setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                        }
                    }))

                    add(HorizontalScrollBinder((0..11).map {
                        HorizontalItemBinder("$it").apply {
                            setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                        }
                    }))

                    val data: List<GoodsBinder> = (0..10).map {
                        GoodsBinder("$it + 商品").apply {
                            setOnClickListener(this@RecyclerViewActivity::onRecycleViewClick)
                        }
                    }

                    add(GoodsGridContainerBinder(data))
                })
                Log.i("goodBinder", "goodData click : ${any.index}")
            }
        }
    }

}
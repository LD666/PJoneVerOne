package com.myfirstapplication.pjoneverone.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.database.FirebaseDatabase
import com.myfirstapplication.pjoneverone.R
import com.myfirstapplication.pjoneverone.fragment.*
import com.myfirstapplication.pjoneverone.myinterf.MyInterface
import com.myfirstapplication.pjoneverone.recycler_data.FirstItemsListData
import kotlinx.android.synthetic.main.cart_recycler_text.*

class MainPageActivity : AppCompatActivity(), MyInterface {

    var itemQlList: ArrayList<Int> = ArrayList()

    override fun updateCart(bundle: Bundle) {

        var passUpdateCart = bundle.getInt("TheTotal")
        var passUpdateCartID = bundle.getString("TheID")
        var passUpdatequl = bundle.getInt("TheQul")
        Log.i("InMinGetItemUpdateCart", passUpdateCart.toString())
        Log.i("InMinGetItemUpdateCart", passUpdateCartID)
        var pass = getSharedPreferences("PassTheTotal", Context.MODE_PRIVATE)
        var editor = pass.edit()
        editor.putInt("passThisTotal", passUpdateCart)
        editor.commit()


        for(i in 0 until saveItem.size){
            if(passUpdateCartID == saveItem[i].itemID){
                saveItem[i].itemQunt = passUpdatequl
            }
        }


        for(i in 0 until saveItem.size){
            itemQlList.add(saveItem[i].itemQunt)
            Log.i("whatNow", saveItem[i].toString())
        }

        var checkOutFragment = CheckOutFragment()

        var bundle = Bundle()
        bundle.putIntegerArrayList("passQla", itemQlList)


        pass.getInt("passThisTotal", 0)
        checkOutFragment.arguments = bundle
//        supportFragmentManager.beginTransaction().add(R.id.main_page, checkOutFragment).commit()



        Log.i("IsInthere",  pass.getInt("passThisTotal", 0).toString())

    }

    var itemIDList: ArrayList<FirstItemsListData> = ArrayList()
    var isIn = 0

    var saveItem: ArrayList<FirstItemsListData> = ArrayList()

    var totalItem: ArrayList<Int> = ArrayList()
    var totalName: ArrayList<String> = ArrayList()
    var totalPrize: ArrayList<String> = ArrayList()
    var totalID: ArrayList<String> = ArrayList()
    var totalImg: ArrayList<String> = ArrayList()

    override fun passItem(bundle: Bundle) {

//        var cartFragment = CartFragment()
        var passItemName = bundle.getString("itemName").toString()
        var passItemPrize = bundle.getString("itemPrize").toString()
        var passItemImg = bundle.getString("itemImg").toString()
        var passItemID = bundle.getString("itemID").toString()
        var passItemQun = 1
        Log.i("InMinGetItem", bundle.getString("itemName"))
        Log.i("InMinGetItem", bundle.getString("itemPrize"))
        Log.i("InMinGetItem", bundle.getString("itemImg"))
        Log.i("InMinGetItem", bundle.getString("itemID"))


        var addData: FirstItemsListData = FirstItemsListData(passItemName, passItemID, passItemPrize, passItemImg, passItemQun)
        var firebaseDatabase = FirebaseDatabase.getInstance()
        var databaseReference = firebaseDatabase.getReference("PushItem")

        var databaseID = databaseReference.push().key

        if (!itemIDList.isEmpty()){
            for(i in 0 until itemIDList.size){
                if(passItemID == itemIDList[i].itemID){
                    itemIDList[i].itemQunt += 1
                    isIn += 1
                    break
                }
            }
        }

        if(isIn == 0){
            itemIDList.add(FirstItemsListData(passItemName, passItemID, passItemPrize, passItemImg, passItemQun))
//            databaseReference.child(databaseID!!).setValue(addData)
        }

        isIn = 0

        for (i in 0 until itemIDList.size){
            Log.i("InItemIDList", itemIDList[i].toString())
        }


        Log.i("InItemIDList", "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF")

    }

    override fun passSubIDandName(bundle: Bundle) {

        var itemFragment = ItemFragment()
        var passSubName = bundle.getString("theSubCatName")
        var passSubID = bundle.getString("theSubCatID")
        Log.i("In MP get", bundle.getString("theSubCatName"))
        Log.i("In MP get", bundle.getString("theSubCatID"))
        bundle.putString("passTheSubCatName", passSubName)
        bundle.putString("passTheSubCatID", passSubID)
        Log.i("In MP pass", bundle.getString("passTheSubCatName"))
        Log.i("In MP pass", bundle.getString("passTheSubCatID"))
        itemFragment.arguments = bundle
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(R.id.main_page, itemFragment).commit()
    }

    override fun passIDandName(bundle: Bundle) {

        var readDataFragment = ReadDataFragment()
        var passName = bundle.getString("theCatName")
        var passID = bundle.getString("theCatID")
        Log.i("In MP get", bundle.getString("theCatName"))
        Log.i("In MP get", bundle.getString("theCatID"))
        bundle.putString("passTheCatName", passName)
        bundle.putString("passTheCatID", passID)
        Log.i("In MP pass", bundle.getString("passTheCatName"))
        Log.i("In MP pass", bundle.getString("passTheCatID"))
        readDataFragment.arguments = bundle
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction().replace(R.id.main_page, readDataFragment).commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        supportActionBar?.hide()

        var mySettingTask = findViewById<BottomNavigationView>(R.id.bottom_nav)

        var bundle = Bundle()
        bundle.getStringArray("AAA")
        Log.i("AAA", bundle.getStringArray("AAA").toString())


        mySettingTask.setOnNavigationItemSelectedListener {item ->

            when (item.itemId) {

                R.id.iteam_setting -> {

                    var settingFragment = SettingFragment()
                    itemIDList.clear()
                    supportFragmentManager.beginTransaction().replace(R.id.main_page, settingFragment).commit()
                    true
                }

                R.id.iteam_account -> {
                    var userFragment = UserFragment()
                    itemIDList.clear()
                    supportFragmentManager.beginTransaction().replace(R.id.main_page, userFragment).commit()
                    true
                }

                R.id.iteam_cart -> {
                    var cartFragment = CartFragment()


                        for (i in 0 until itemIDList.size){
                            totalItem.add(itemIDList[i].itemQunt)
                            totalName.add(itemIDList[i].itemName)
                            totalID.add(itemIDList[i].itemID)
                            totalImg.add(itemIDList[i].itemImg)
                            totalPrize.add(itemIDList[i].itemPrize)
                        }

                        for (i in 0 until totalItem.size){
                            Log.i("InItemIDList", totalItem[i].toString())
                        }

                        var passAllPrize = 0

                        for (i in 0 until totalPrize.size){
                            passAllPrize += totalPrize[i].toInt() * totalItem[i]
                        }

                        var bundle = Bundle()
                        bundle.putIntegerArrayList("qlaArray", totalItem)
                        bundle.putStringArrayList("nameArray", totalName)
                        bundle.putStringArrayList("IDArray", totalID)
                        bundle.putStringArrayList("ImgArray", totalImg)
                        bundle.putStringArrayList("prizeArray", totalPrize)
                        bundle.putInt("All", passAllPrize)

                        Log.i("allPrize", passAllPrize.toString())

                        cartFragment.arguments = bundle

                    saveItem = itemIDList

                    supportFragmentManager.beginTransaction().replace(R.id.main_page, cartFragment).commit()

                    true
                }


                else -> false
            }

        }


        var mainPageFrag = MainPageFragment()
        supportFragmentManager.beginTransaction().replace(R.id.main_page, mainPageFrag).commit()

    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }
}

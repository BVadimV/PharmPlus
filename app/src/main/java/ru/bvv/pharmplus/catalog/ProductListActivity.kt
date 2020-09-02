package ru.bvv.pharmplus.catalog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.bvv.pharmplus.DBHelper
import ru.bvv.pharmplus.R
import ru.bvv.pharmplus.catalog.product.ProductItem

class ProductListActivity : AppCompatActivity() {

    internal var dbHelper = DBHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_list)


        val productList = generateDummyList()
    }

    private fun generateDummyList(): List<ProductItem> {

        val list = ArrayList<ProductItem>()

        // Will do SQLite database
//        try {
//            val db: SQLiteDatabase = dbHelper.readableDatabase
//            val cursor: Cursor = db.query("medicines", listOf<String>(){"NAME", "CHARACTERISTIC", "COMPOSITION",
//                "PHARMACOLOGY_EFFECT", "INDICATION_FOR_USE", "CONTRAINDICATIONS", "SIDE_EFFECTS",
//                "MODE_OF_APPLICATION", "RELEASE_FORM", "SHELF_LIFE", "COST", "CATEGORY", "IMAGE"},
//            "_ID = ?",)
//        }


        return list
    }
}
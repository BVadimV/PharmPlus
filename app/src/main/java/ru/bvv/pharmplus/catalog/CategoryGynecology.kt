package ru.bvv.pharmplus.catalog

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import ru.bvv.pharmplus.*
import ru.bvv.pharmplus.catalog.product.ProductList

class CategoryGynecology : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    override fun onItemClick(position: Int) {
        when (position) {
            0 -> {
                val intent = Intent(this, ProductList::class.java)
                intent.putExtra(ProductList.EXTRA_CATEGORY, "infertility")
                startActivity(intent)
            }
            1 -> {
                val intent = Intent(this, ProductList::class.java)
                intent.putExtra(ProductList.EXTRA_CATEGORY, "infection")
                startActivity(intent)
            }
            2 -> {
                val intent = Intent(this, ProductList::class.java)
                intent.putExtra(ProductList.EXTRA_CATEGORY, "gynecology")
                startActivity(intent)
            }
            3 -> {
                val intent = Intent(this, ProductList::class.java)
                intent.putExtra(ProductList.EXTRA_CATEGORY, "gynecology")
                startActivity(intent)
            }
            4 -> {
                val intent = Intent(this, ProductList::class.java)
                intent.putExtra(ProductList.EXTRA_CATEGORY, "gynecology")
                startActivity(intent)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val categoryList = generateNewValues()
        val adapter = RecyclerViewAdapter(categoryList, this)

        val listItem = findViewById<RecyclerView>(R.id.list_item_category)
        listItem.adapter = adapter
        listItem.layoutManager = LinearLayoutManager(this)
        listItem.setHasFixedSize(true)
    }

    private fun generateNewValues(): List<SimpleCategoryItem> {
        val arrayCategory = resources.getStringArray(R.array.list_gynecology)
        val values = ArrayList<SimpleCategoryItem>()

        for ((index, item) in arrayCategory.withIndex()){
            val itemArray = SimpleCategoryItem(item)
            values += itemArray
        }

        return values
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                val intent = Intent (this@CategoryGynecology, ProfileActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_catalog -> {
                val intent = Intent (this@CategoryGynecology, CategoryActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_map -> {
                val intent = Intent (this@CategoryGynecology, MapActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_shopping_cart -> {
                val intent: Intent = Intent (this@CategoryGynecology, ShoppingCartActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.action_contacts -> {
                val intent = Intent (this@CategoryGynecology, ContactsActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.sign_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent (this@CategoryGynecology, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
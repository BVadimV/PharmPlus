package ru.bvv.pharmplus.catalog.medicines

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import ru.bvv.pharmplus.*
import ru.bvv.pharmplus.catalog.RecyclerViewAdapter
import ru.bvv.pharmplus.catalog.SimpleCategoryItem

class CategoryGynecology : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {

    override fun onItemClick(position: Int) {
//        when (position) {
//            0 -> {
//                val Intent = Intent(this, CategoryGynecology::class.java)
//            }
//        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

// Не работает. крашится приложение.
//        lateinit var toolbar: Toolbar
//        setSupportActionBar(toolbar.findViewById(R.id.toolbar))

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
                val intent = Intent (this@CategoryGynecology, CategoryGynecology::class.java)
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
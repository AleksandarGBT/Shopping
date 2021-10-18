package com.example.shopping.DatabaseFiles;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.shopping.GroceryItem;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

@Database(entities = {GroceryItem.class, CartItem.class}, version = 1)
public abstract class ShopDatabase extends RoomDatabase {

    public abstract GroceryItemDao groceryItemDao();
    public abstract CartItemDao cartItemDao();

    private static ShopDatabase instance;

    public static synchronized ShopDatabase getInstance(Context context) {
        if (null == instance) {
            instance = Room.databaseBuilder(context, ShopDatabase.class, "shop_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(initialCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback initialCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull @NotNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new InitialAsyncTask(instance).execute();
        }
    };

    private static class InitialAsyncTask extends AsyncTask<Void,Void,Void> {

        private GroceryItemDao groceryItemDao;

        public InitialAsyncTask(ShopDatabase db) {
            this.groceryItemDao = db.groceryItemDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            ArrayList<GroceryItem> allItems = new ArrayList<>();

            GroceryItem iceCream = new GroceryItem("Ice Cream", "Ice cream is a sweetened frozen food typically eaten as a snack or dessert. It may be made from dairy milk or cream and is flavoured with a sweetener, either sugar or an alternative, and a spice, such as cocoa or vanilla, or with fruit such as strawberries or peaches.",
                    "https://media.pakfactory.com/catalog/product/cache/c7a30112d165d3b39569235f6bc121aa/i/c/ice-cream-packaging-boxes_1.jpg",
                    "Food", 5.4, 10);
            allItems.add(iceCream);

            GroceryItem milk = new GroceryItem("Milk",
                    "Milk is a nutrient-rich, white liquid food produced bu the mammary glands of mammals.It is the primary source of nutrition for young mammals, including breastfed human infants before they are able to digest solid food.",
                    "https://www.psdmockups.com/wp-content/uploads/2019/06/1L-Tetra-Pak-Carton-Boxes-PSD-Mockup.jpg",
                    "Drink", 2.3,8);
            allItems.add(milk);



            GroceryItem soda = new GroceryItem("Soda", "A soft drink is a drink that usually contains water (often carbonated), a sweetener, and a natural and/or artificial flavoring. The sweetener may be a sugar, high-fructose corn syrup, fruit juice, a sugar substitute (in the case of diet drinks), or some combination of these. Soft drinks may also contain caffeine, colorings, preservatives, and/or other ingredients.",
                    "https://cdn.diffords.com/contrib/bws/2019/05/5cc9b8261f976.jpg",
                    "Drink", 0.99, 15);
            allItems.add(soda);

            GroceryItem shampoo = new GroceryItem("Shampoo", "Shampoo is a hair care product, typically in the form of a viscous liquid, that is used for cleaning hair. Less commonly, shampoo is available in bar form, like a bar of soap. Shampoo is used by applying it to wet hair, massaging the product into the scalp, and then rinsing it out. Some users may follow a shampooing with the use of hair conditioner.",
                    "http://unblast.com/wp-content/uploads/2020/02/Shampoo-Bottles-Mockup-2.jpg",
                    "Cleanser", 14.5, 9);
            allItems.add(shampoo);

            GroceryItem spaghetti = new GroceryItem("Spaghetti","Spaghetti is a long, thin, solid, cylindrical pasta. It is a staple food of traditional Italian cuisine. Like other pasta, spaghetti is made of milled wheat and water and sometimes enriched with vitamins and minerals. Italian spaghetti is typically made from durum wheat semolina. Usually the pasta is white because refined flour is used, but whole wheat flour may be added. Spaghettoni is a thicker form of spaghetti, while capellini is a very thin spaghetti.",
                    "https://static.turbosquid.com/Preview/001154/937/BG/packet-spaghetti-pack-3D_0.jpg",
                    "Food", 3.85, 6);
            allItems.add(spaghetti);

            GroceryItem soap = new GroceryItem("Soap","Soap is a salt of a fatty acid used in a variety of cleansing and lubricating products. In a domestic setting, soaps are surfactants usually used for washing, bathing, and other types of housekeeping. In industrial settings, soaps are used as thickeners, components of some lubricants, and precursors to catalysts.",
                    "https://static.chemistwarehouse.co.nz/ams/media/pi/53058/3DF_800.jpg",
                    "Cleanser", 2.65,14);
            allItems.add(soap);

            GroceryItem juice = new GroceryItem("Juice","Juice is a drink made from the extraction or pressing of the natural liquid contained in fruit and vegetables. It can also refer to liquids that are flavored with concentrate or other biological food sources, such as meat or seafood, such as clam juice. Juice is commonly consumed as a beverage or used as an ingredient or flavoring in foods or other beverages, as for smoothies. Juice emerged as a popular beverage choice after the development of pasteurization methods enabled its preservation without using fermentation (which is used in wine production).",
                    "https://preview.free3d.com/img/2016/08/2154853686805792546/xltuq87k-900.jpg",
                    "Drink",3.45,25);
            allItems.add(juice);

            GroceryItem walnut = new GroceryItem("Walnut", "A walnut is the nut of any tree of the genus Juglans (family Juglandaceae), particularly the Persian or English walnut, Juglans regia. A walnut is the edible seed of a drupe, and thus not a true botanical nut. It is commonly consumed as a nut. After full ripening for its edible seed when the shell has been discarded, it is used as a garnish or a snack. Nuts of the eastern black walnut (Juglans nigra) and butternuts (Juglans cinerea) are less commonly consumed.",
                    "https://5.imimg.com/data5/JR/AK/VJ/SELLER-2793878/walnut-500x500.jpg",
                    "Nuts",5.6,4);
            allItems.add(walnut);

            GroceryItem pistachio = new GroceryItem("Pistachio", "The pistachio a member of the cashew family, is a small tree originating from Central Asia and the Middle East. The tree produces seeds that are widely consumed as food. Pistacia vera often is confused with other species in the genus Pistacia that are also known as pistachio. These other species can be distinguished by their geographic distributions (in the wild) and their seeds which are much smaller and have a soft shell.",
                    "https://www.irandriedfruit.com/wp-content/uploads/2019/05/Pistachio-history.jpg",
                    "Nuts",9.85,15);
            allItems.add(pistachio);

            for (GroceryItem g: allItems) {
                groceryItemDao.insert(g);
            }

            return null;
        }
    }
}

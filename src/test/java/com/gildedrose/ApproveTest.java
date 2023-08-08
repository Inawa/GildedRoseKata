package com.gildedrose;

import org.approvaltests.combinations.CombinationApprovals;
import org.junit.jupiter.api.Test;
import org.approvaltests.Approvals;

public class ApproveTest {

    @Test
    public void test_approval() {
        Item[] items = new Item[] { new Item("foo", 0, 10) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();

        Approvals.verifyAll("", app.items);
    }

    @Test
    public void update_quality() {
        CombinationApprovals.verifyAllCombinations(
                (name, sellIn, quality) -> {
                    Item[] items = new Item[] { new Item(name, sellIn, quality) };
                    GildedRose app = new GildedRose(items);
                    app.updateQuality();
                    return app.items[0];
                },
                new String[] {"foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros"},
                new Integer[] { -1, 0, 20},
                new Integer[] { 0, 1, 49, 60 });

//        new String[] { "foo", "Aged Brie", "Backstage passes to a TAFKAL80ETC concert", "Sulfuras, Hand of Ragnaros" },
//            new Integer[] { -1, 0, 1, 5, 6, 10, 11 },
//            new Integer[] { 0, 1, 49, 50 });
    }
}

package ex.microservices.inventoryservice.util;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import ex.microservices.inventoryservice.model.InventoryItem;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserCsv {
    public static List<InventoryItem> parse(InputStream inputStream) {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        CsvParserSettings settings = new CsvParserSettings();
        settings.setHeaderExtractionEnabled(true);
        settings.setMaxCharsPerColumn(100000);
        CsvParser csvParser = new CsvParser(settings);
        List<Record> records = csvParser.parseAllRecords(inputStream);
        records.forEach(record -> {
            InventoryItem item = new InventoryItem();
            item.setSku(record.getString("sku"));
            item.setProductCode(record.getString("uniq_id"));
            item.setAmount(record.getInt("amount"));
            inventoryItems.add(item);
        });
        return inventoryItems;
    }
}

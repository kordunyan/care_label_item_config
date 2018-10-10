package com.itemconfiguration.export.bilder;

import com.itemconfiguration.export.bilder.line.StaticLines;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class BaseScreeptsBuilder {

    public String buildDeclarations() {
        return new StringBuilder()
                .append("DO").append(StaticLines.NEW_LINE)
                .append("$$").append(StaticLines.NEW_LINE)
                .append("DECLARE").append(StaticLines.NEW_LINE)
                .append("  field_set_id INTEGER;").append(StaticLines.NEW_LINE)
                .append("  var_item_id BIGINT;").append(StaticLines.NEW_LINE)
                .append("BEGIN").append(StaticLines.NEW_LINE)
                .append("  ALTER TABLE purchase_order").append(StaticLines.NEW_LINE)
                .append("  DROP CONSTRAINT purchase_order_item_id_fkey;").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("  ALTER TABLE label").append(StaticLines.NEW_LINE)
                .append("  DROP CONSTRAINT label_item_id_fkey;").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
        .toString();
    }


    public List<String> buildDeleteItemsData(List<String> itemNumbers) {
        List<String> result = new ArrayList<>();
        result.add("FOR var_item_id IN SELECT i.id FROM item i JOIN field f ON f.field_set_id = i.field_set_id AND f.field_config_name = 'D2COMM_ITEM_NUMBER' WHERE f.value IN (");
        result.add(StaticLines.NEW_LINE);
        result.add(buildJoinedItemNumbers(itemNumbers));
        result.add(")" + StaticLines.NEW_LINE);
        result.add(buildDeleteLoop());
        return result;
    }

    private String buildJoinedItemNumbers(List<String> itemNumbers) {
        return itemNumbers.stream()
                .map(BuilderUtils::escapeValue)
                .collect(Collectors.joining("," + StaticLines.NEW_LINE));
    }

    private String buildDeleteLoop() {
        return new StringBuilder()
                .append(" LOOP").append(StaticLines.NEW_LINE)
                .append("  RAISE INFO E'\\n\\nDelete item info: %', var_item_id;").append(StaticLines.NEW_LINE)
                .append("  DELETE FROM mandatory_translation ").append(StaticLines.NEW_LINE)
                .append("  WHERE item_field_config_id IN (").append(StaticLines.NEW_LINE)
                .append("   SELECT id FROM item_field_config WHERE item_id = var_item_id ").append(StaticLines.NEW_LINE)
                .append("  );").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("  DELETE FROM mandatory_field").append(StaticLines.NEW_LINE)
                .append("  WHERE item_field_config_id IN (").append(StaticLines.NEW_LINE)
                .append("   SELECT id FROM item_field_config WHERE item_id = var_item_id").append(StaticLines.NEW_LINE)
                .append("  );").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("  DELETE FROM item_field_config").append(StaticLines.NEW_LINE)
                .append("  WHERE item_id = var_item_id;").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("  DELETE FROM item").append(StaticLines.NEW_LINE)
                .append("  WHERE id = var_item_id;").append(StaticLines.NEW_LINE)
                .append(" END LOOP;").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .toString();
    }

    public String buildFinalScreepts() {
        return new StringBuilder()
                .append(StaticLines.NEW_LINE)
                .append("ALTER TABLE purchase_order").append(StaticLines.NEW_LINE)
                .append("ADD CONSTRAINT purchase_order_item_id_fkey FOREIGN KEY (item_id)").append(StaticLines.NEW_LINE)
                .append("REFERENCES item (id) MATCH SIMPLE").append(StaticLines.NEW_LINE)
                .append("ON UPDATE CASCADE ON DELETE RESTRICT;").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("ALTER TABLE label").append(StaticLines.NEW_LINE)
                .append("ADD CONSTRAINT label_item_id_fkey FOREIGN KEY (item_id)").append(StaticLines.NEW_LINE)
                .append("REFERENCES item (id)").append(StaticLines.NEW_LINE)
                .append("ON UPDATE CASCADE ON DELETE RESTRICT;").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("PERFORM setval('item_id_seq', COALESCE((SELECT MAX(id)+1 FROM item), 1), false);").append(StaticLines.NEW_LINE)
                .append(StaticLines.NEW_LINE)
                .append("END").append(StaticLines.NEW_LINE)
                .append("$$;").append(StaticLines.NEW_LINE)
                .toString();
    }

}

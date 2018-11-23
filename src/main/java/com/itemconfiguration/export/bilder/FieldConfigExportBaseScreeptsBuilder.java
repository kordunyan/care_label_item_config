package com.itemconfiguration.export.bilder;

import com.itemconfiguration.export.bilder.line.StaticLines;
import org.springframework.stereotype.Component;

@Component
public class FieldConfigExportBaseScreeptsBuilder {

    public String buildDeclarations() {
        return new StringBuilder()
                .append("DO").append(StaticLines.NEW_LINE)
                .append("$$").append(StaticLines.NEW_LINE)
                .append("DECLARE").append(StaticLines.NEW_LINE)
                .append("   var_item_field_config RECORD;").append(StaticLines.NEW_LINE)
                .append("   var_data_type RECORD;").append(StaticLines.NEW_LINE)
                .append("BEGIN").append(StaticLines.NEW_LINE)
                .append("   FOR var_data_type IN SELECT * FROM ( VALUES").append(StaticLines.NEW_LINE)
                .toString();
    }

    public String buildDataTypesInsertionScreept() {
        return new StringBuilder()
                .append(") AS t (name, is_instruction)").append(StaticLines.NEW_LINE)
                .append("LOOP").append(StaticLines.NEW_LINE)
                .append("   PERFORM 1 FROM data_type WHERE name = var_data_type.name;").append(StaticLines.NEW_LINE)
                .append("   IF NOT FOUND THEN").append(StaticLines.NEW_LINE)
                .append("       RAISE NOTICE 'Insert data type: %', var_data_type.name;").append(StaticLines.NEW_LINE)
                .append("       INSERT INTO data_type(name, is_instruction)").append(StaticLines.NEW_LINE)
                .append("       VALUES (var_data_type.name, var_data_type.is_instruction);").append(StaticLines.NEW_LINE)
                .append("   END IF;").append(StaticLines.NEW_LINE)
                .append("END LOOP;").append(StaticLines.NEW_LINE)
                .append("FOR var_item_field_config IN SELECT * FROM ( VALUES").append(StaticLines.NEW_LINE)
                .toString();
    }

    public String buildFieldConfigsInsertionScreept() {
        return new StringBuilder()
                .append("   ) AS t (name, type, owner, is_printable)").append(StaticLines.NEW_LINE)
                .append("   LOOP").append(StaticLines.NEW_LINE)
                .append("   PERFORM 1 FROM field_config WHERE name = var_item_field_config.name;").append(StaticLines.NEW_LINE)
                .append("   IF FOUND THEN ").append(StaticLines.NEW_LINE)
                .append("       RAISE NOTICE 'UPDATE field config: %', var_item_field_config.name;").append(StaticLines.NEW_LINE)
                .append("       UPDATE field_config SET").append(StaticLines.NEW_LINE)
                .append("           type = var_item_field_config.type, ").append(StaticLines.NEW_LINE)
                .append("           owner = var_item_field_config.owner, ").append(StaticLines.NEW_LINE)
                .append("           is_printable = var_item_field_config.is_printable").append(StaticLines.NEW_LINE)
                .append("       WHERE name = var_item_field_config.name;").append(StaticLines.NEW_LINE)
                .append("   ELSE").append(StaticLines.NEW_LINE)
                .append("       RAISE NOTICE 'INSERT field config: %', var_item_field_config.name;").append(StaticLines.NEW_LINE)
                .append("       INSERT INTO field_config(name, type, owner, is_printable)").append(StaticLines.NEW_LINE)
                .append("       VALUES (var_item_field_config.name, var_item_field_config.type, var_item_field_config.owner, var_item_field_config.is_printable);").append(StaticLines.NEW_LINE)
                .append("   END IF;").append(StaticLines.NEW_LINE)
                .append("   END LOOP; ").append(StaticLines.NEW_LINE)
                .append("END").append(StaticLines.NEW_LINE)
                .append("$$;").append(StaticLines.NEW_LINE)
                .toString();
    }

}

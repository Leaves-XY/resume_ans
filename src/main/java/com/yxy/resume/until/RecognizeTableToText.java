package com.yxy.resume.until;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2023/6/7 15:40
 */

@Component
public class RecognizeTableToText {
    /**
     * 从 表格识别完的JSON 中提取文本
     * @param json
     * @return
     * @throws Exception
     */
    public static String recognizeTableToText(String json) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode tableDetectionsNode = rootNode.path("TableDetections");
        StringBuilder context = new StringBuilder();
        for (JsonNode detectionNode : tableDetectionsNode) {
            JsonNode cellsNode = detectionNode.path("Cells");

            List<JsonNode> cellList = new ArrayList<>();
            cellsNode.forEach(cellList::add);
            Collections.sort(cellList, (JsonNode a, JsonNode b) -> {
                int aY = a.path("Polygon").get(0).path("Y").asInt();
                int bY = b.path("Polygon").get(0).path("Y").asInt();
                return Integer.compare(aY, bY);
            });

            for (JsonNode cellNode : cellList) {
                String text = cellNode.path("Text").asText();
                context.append(text);
                if (!text.trim().equals("") && !text.isEmpty())
                    context.append(System.lineSeparator());  // Add a newline after each cell
            }
        }

        System.out.println(context);
        return context.toString().replaceAll("\\s", "").replaceAll("\\n", "");
    }


}







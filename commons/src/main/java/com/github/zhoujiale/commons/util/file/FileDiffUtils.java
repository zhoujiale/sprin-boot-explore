package com.github.zhoujiale.commons.util.file;

import com.github.difflib.DiffUtils;
import com.github.difflib.algorithm.myers.MeyersDiffWithLinearSpace;
import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 文件差异工具
 *
 * @author zhoujiale
 */
@Slf4j
public class FileDiffUtils {

    private static final String NEW_LINE = "\n";

    /**
     * 新增文本开始
     **/
    private static final String DELETE_OPEN = "<del class=\"del-class\">";

    /**
     * 删除文本结束
     **/
    private static final String DELETE_CLOSE = "</del>";

    /**
     * 新增文本开始
     **/
    private static final String INSERT_OPEN = "<ins class=\"inc-class\">";

    /**
     * 新增文本结束
     **/
    private static final String INSERT_CLOSE = "</ins>";

    static {
        Map<String, String> KEY_MAP = new HashMap<>(4);
        KEY_MAP.put("<strong>", "@strong@");
        KEY_MAP.put("</strong>", "@/strong@");
        KEY_MAP.put("<em>", "@em@");
        KEY_MAP.put("</em>", "@/em@");
        Map<String, String> REVERSE_KEY_MAP = new HashMap<>(4);
        REVERSE_KEY_MAP.put("@strong@", "<strong>");
        REVERSE_KEY_MAP.put("@/strong@", "</strong>");
        REVERSE_KEY_MAP.put("@em@", "<em>");
        REVERSE_KEY_MAP.put("@/em@", "</em>");
    }

    /**
     * 比对文本
     * @param originalContent
     * @param revisedContent
     * @return
     */
    public static String buildDocumentDiff(String originalContent, String revisedContent) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> originalList = nonNullElementsIn(originalContent.split(NEW_LINE));
        List<String> revisedList = nonNullElementsIn(revisedContent.split(NEW_LINE));
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(false)
                .mergeOriginalRevised(true)
                .decompressDeltas(false)
                .oldTag((tag, f) -> f ? DELETE_OPEN : DELETE_CLOSE)
                .newTag((tag, f) -> f ? INSERT_OPEN : INSERT_CLOSE)
                .build();
        List<DiffRow> diffRows = generator.generateDiffRows(originalList,
                DiffUtils.diff(originalList, revisedList, new MeyersDiffWithLinearSpace<>()));
        diffRows.forEach(d -> stringBuilder.append(d.getOldLine()));
        return StringEscapeUtils.unescapeHtml4(stringBuilder.toString());
    }


    /**
     * 数组转列表
     * @param array
     * @return
     */
    private static List<String> nonNullElementsIn(String[] array) {
        return null == array ? Collections.emptyList() : Arrays.stream(array).filter(Objects::nonNull).collect(Collectors.toList());
    }
}

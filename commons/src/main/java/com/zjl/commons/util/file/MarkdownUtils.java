package com.zjl.commons.util.file;

import com.vladsch.flexmark.ext.abbreviation.AbbreviationExtension;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.parser.ParserEmulationProfile;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * markdown工具类
 */
@Slf4j
public class MarkdownUtils {

    private static final Pattern YAML = Pattern.compile("(---\r|---\n)");

    /**
     * markdown转html
     * @param markdown markdown格式的字符串
     * @return java.lang.String
     */
    public static String markdownToHtml(String markdown){
        Matcher matcher = YAML.matcher(markdown);
        int count = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()){
            count++;
            matcher.appendReplacement(stringBuffer,count % 2 == 1?"```yaml\n":"```\n");
        }
        matcher.appendTail(stringBuffer);
        markdown = stringBuffer.toString().replaceAll("(\r)", StringUtils.EMPTY)
                .replaceAll("([^|]\\n\\|)","$1\n|")
                .replaceAll("([|]\\n)(?!\\|)","$1\n")
                .replaceAll("(\\[toc\\]\\n)","[TOC html]\n");
        MutableDataSet options = new MutableDataSet();
        options.setFrom(ParserEmulationProfile.KRAMDOWN);
        options.set(Parser.EXTENSIONS, Arrays.asList(
                AsideExtension.create(),
                AbbreviationExtension.create(),
                AttributesExtension.create(),
                DefinitionExtension.create(),
                FootnoteExtension.create(),
                TocExtension.create(),
                TablesExtension.create(),
                TypographicExtension.create(),
                MediaTagsExtension.create(),
                YamlFrontMatterExtension.create()
        ));
        options.set(TocExtension.IS_HTML,true);
        options.set(AttributesExtension.FENCED_CODE_INFO_ATTRIBUTES,true);
        //自定义目录样式
        options.set(TocExtension.LIST_CLASS,"catalogue");
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();
        Node document = parser.parse(markdown);
        String result = renderer.render(document);
        if (result.endsWith("\n")){
            result = result.substring(0,result.length() - 1);
        }
        return result;
    }
}

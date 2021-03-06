package org.wikimedia.search.querystring.query;

import java.util.List;

import org.apache.lucene.search.FuzzyQuery;
import org.apache.lucene.search.MultiTermQuery.RewriteMethod;
import org.apache.lucene.search.Query;

/**
 * Builds queries that reference one or more fields.
 */
public interface FieldQueryBuilder {
    Query termQuery(String term);

    Query phraseQuery(List<PhraseTerm> terms, int slop, boolean useQuotedTerm);

    /**
     * Build a fuzzy query.
     *
     * @param term the term to fuzzy match
     * @param similaritySpec the similarity specification to use. It maps to the
     *            number of edits. Use Float.NEGATIVE_INFINITY to use the
     *            default
     * @return the fuzzy query
     */
    Query fuzzyQuery(String term, float similaritySpec);

    Query prefixQuery(String term);

    Query wildcardQuery(String term);

    Query regexQuery(String regex);

    Query fieldExists();

    /**
     * Settings used by FieldQueryBuilders.
     */
    public static class Settings {
        private int maxPhraseSlop = 20;
        private int termQueryPhraseSlop = 0;
        private float defaultFuzzySimilaritySpec = .6f;
        private int fuzzyPrefixLength = FuzzyQuery.defaultPrefixLength;
        private RewriteMethod rewriteMethod;
        private int fuzzyMaxExpansions = FuzzyQuery.defaultMaxExpansions;
        private boolean allowLeadingWildcard;
        private boolean allowPrefix = true;
        private boolean shouldUseFieldNamesFieldForExists = false;
        private RegexQueryBuilder regexQueryBuilder = RegexQueryBuilder.NONE;

        public int getMaxPhraseSlop() {
            return maxPhraseSlop;
        }

        public void setMaxPhraseSlop(int maxPhraseSlop) {
            this.maxPhraseSlop = maxPhraseSlop;
        }

        /**
         * Slop used in phrase queries generated when terms analyze to multiple
         * tokens.
         */
        public int getTermQueryPhraseSlop() {
            return termQueryPhraseSlop;
        }

        public void setTermQueryPhraseSlop(int termQueryPhraseSlop) {
            this.termQueryPhraseSlop = termQueryPhraseSlop;
        }

        public float getDefaultFuzzySimilaritySpec() {
            return defaultFuzzySimilaritySpec;
        }

        public void setDefaultFuzzySimilaritySpec(float defaultFuzzySimilaritySpec) {
            this.defaultFuzzySimilaritySpec = defaultFuzzySimilaritySpec;
        }

        public int getFuzzyPrefixLength() {
            return fuzzyPrefixLength;
        }

        public void setFuzzyPrefixLength(int fuzzyPrefixLength) {
            this.fuzzyPrefixLength = fuzzyPrefixLength;
        }

        public RewriteMethod getRewriteMethod() {
            return rewriteMethod;
        }

        public void setRewriteMethod(RewriteMethod rewriteMethod) {
            this.rewriteMethod = rewriteMethod;
        }

        public int getFuzzyMaxExpansions() {
            return fuzzyMaxExpansions;
        }

        public void setFuzzyMaxExpansions(int fuzzyMaxExpansions) {
            this.fuzzyMaxExpansions = fuzzyMaxExpansions;
        }

        /**
         * Is it ok if a wildcard search starts with a wildcard? Those are much
         * more expensive then when they don't start with a wildcard.
         */
        public boolean getAllowLeadingWildcard() {
            return allowLeadingWildcard;
        }

        /**
         * Set whether it is ok if a wildcard search starts with a wildcard?
         * Those are much more expensive then when they don't start with a
         * wildcard.
         */
        public void setAllowLeadingWildcard(boolean allowLeadingWildcard) {
            this.allowLeadingWildcard = allowLeadingWildcard;
        }

        public boolean getAllowPrefix() {
            return allowPrefix;
        }

        public void setAllowPrefix(boolean allowPrefix) {
            this.allowPrefix = allowPrefix;
        }

        public boolean getShouldUseFieldNamesFieldForExists() {
            return shouldUseFieldNamesFieldForExists;
        }

        public void setShouldUseFieldNamesFieldForExists(boolean shouldUseFieldNamesFieldForExists) {
            this.shouldUseFieldNamesFieldForExists = shouldUseFieldNamesFieldForExists;
        }

        public RegexQueryBuilder getRegexQueryBuilder() {
            return regexQueryBuilder;
        }

        public void setRegexQueryBuilder(RegexQueryBuilder regexQueryBuilder) {
            this.regexQueryBuilder = regexQueryBuilder;
        }
    }
}
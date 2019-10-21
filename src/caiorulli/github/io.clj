(ns caiorulli.github.io
  (:require [stasis.core :as stasis]))

(defn- get-pages []
  {"/index.html" "<h1>Hello world!</h1>"})

(def app
  (stasis/serve-pages get-pages))

(def target-dir "./docs")

(defn export []
  (stasis/empty-directory! target-dir)
  (stasis/export-pages (get-pages) target-dir))

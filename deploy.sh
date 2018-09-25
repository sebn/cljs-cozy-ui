#!/bin/bash
set -e
yarn clean
yarn shadow-cljs release app
cd public
git init
git add .
git commit -m "Deploy"
git push --force --quiet "git@github.com:sebn/cljs-cozy-ui.git" master:gh-pages
rm -fr .git
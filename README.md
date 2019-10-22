# caiorulli.github.io

Development project for generating the assets for my [personal site](https://caiorulli.github.io).

It has a handful of Clojure tools for static sites:
- [stasis](https://github.com/magnars/stasis) for static page serving utils;
- [hiccup](https://github.com/weavejester/hiccup) for building HTML;
- [garden](https://github.com/noprompt/garden) for building CSS.

## Usage

To run the project with stasis' live-reload:
```bash
lein ring server-headless
```
It will start at `localhost:3000` by default.

To build it into the `caiorulli.github.io` submodule, run:
```bash
lein build-site
```

## License

Copyright © 2019 FIXME

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.

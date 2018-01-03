# Applying The Apache 2.0 License

The APACHE 2.0 license should be applied at the highest point comments are allowed in a file in a language's
block comment, or if the language does not have a block comment, it should be applied as several line comments.

Here's some examples of applying the license *correctly*:
**Lua**
```lua
--[[
-- Copyright YYYY Author One & Author Two & Author Three
-- 
-- Licensed under the Apache License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
-- 
--     http://www.apache.org/licenses/LICENSE-2.0
-- 
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- ]]
```

What is done right?
1) Each line (including empty lines) has a `--`.
2) The block respects the empty lines found in the original license, and
   properly continues to prefix them with `--`.
3) The link `http://www.apache.org/licenses/LICENSE-2.0` is indented with
   four spaces to the right of being aligned with the rest of the copyright header.

**Bash**
```bash
# Copyright YYYY Author One & Author Two & Author Three
# 
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
#     http://www.apache.org/licenses/LICENSE-2.0
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
```

What is done right?
1) All comment flags have a single space inbetween them and the comment content
   (note this applies to block comments as well).
2) Empty lines are also commented out.
3) There are no leading or trailing empty lines commented out.

Here's some examples of applying the license *incorrectly*:
**Lua**
```lua
--[[
  Copyright YYYY Author One & Author Two & Author Three
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
  http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License. 
-- ]]
```

What is done wrong?
1) Missing `--` on each line
2) Missing empty lines in places they are required
3) The link `http://www.apache.org/licenses/LICENSE-2.0` is not indented with 
   four spaces, and is aligned with the rest of the copyright

**Bash**
```bash
#
#Copyright YYYY Author One & Author Two & Author Three
 
#Licensed under the Apache License, Version 2.0 (the "License");
#you may not use this file except in compliance with the License.
#You may obtain a copy of the License at
 
#    http://www.apache.org/licenses/LICENSE-2.0
 
#Unless required by applicable law or agreed to in writing, software
#distributed under the License is distributed on an "AS IS" BASIS,
#WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
#See the License for the specific language governing permissions and
#limitations under the License.
#
```

What is done wrong?
1) Empty lines are not commented
2) There exists a leading and trailing commented line for the header.
3) Each line does not have a single space inbetween the comment flag (`#`) and
   the actual comment.

### When to Apply?
The real question to ask is "when **not** to apply".

All JSON formatted files (`.patch`, `.animation`, `.frames`, etc) **should not**
have license applied to them, and normal text files and meta-data files (such as
`.gitignore` and `.gitattributes`) should similarly **not** have the license applied.
Anything else that has a form of commenting should follow the guildlines above and
apply the copyright.

# Getting Started

If you're new to git, read this section. Otherwise, skip ahead to [How To Contribute](#how-to-contribute).

Git is a version control system used to keep track of changes and coordinate work between multiple people. It's extremely powerful, but also confusing and intimidating at first. This document should help clear things up for you.

First off: installation. If you're on *nix, simply install git through your package manager. OS X and Windows users can find the command line version of git [here](https://git-scm.com/downloads) or install [GitHub Desktop](https://desktop.github.com/). 

*nix users can use `git-gui` for a GUI tool or install one of the various clients found [here](https://git-scm.com/downloads/guis).

Now that you have git installed, you'll want to learn how to use it. You can refer to the [git docs](https://git-scm.com/doc). [Codecademy](https://www.codecademy.com/) also has a git tutorial that I found quite helpful.

Finally, you'll want to clone the repository to your machine.

# How To Contribute

First and foremost: *never commit directly to master*! The only time anybody should commit to master is for tiny changes. Anything else should be done through pull requests.

## Branching Out

If you don't have access to the repository you should make a fork, and you can then commit directly to your fork's `master`. You should [add the original repository as a remote](https://help.github.com/articles/configuring-a-remote-for-a-fork/).

Otherwise, you'll want to create a new local branch with `git checkout -b my_branch_name`. `git checkout` is used to switch between existing branches. The `-b` option tells git to create the branch if it does not already exist. Don't include the option if the branch you're switching to already exists!

> ### You Shouldn't Ignore It!
> A `.gitignore` is a file that prevents (or forces)
> certain files from getting pushed to the GitHub repo
>
> Typically these are "editor-specific" files, stuff
> a development environment (the thing you type code in)
> creates to make your life easier!
>
> Unfortunately, these files can make maintaining a
> project very hard if everyone decides to commit them,
> and would cause it to be full of useless and random
> files that cause confusion to someone who might be new
> to contributing to a multi-developer project!
>
> We recommend you do some research into whatever development
> environment you might use and add them to the `.girignore`
> [here](https://github.com/ColonelBirdstrong/MH-StarboundRaces/blob/master/.gitattributes)

## A Big Commitment

Now, make your changes and commit them. `git status` will tell you which files have been changed and if they have been staged for commit. You can add unstaged files with `git add` and remove deleted files with `git rm`. To commit, simply do `git commit -m "My commit message"`. You can amend your latest commit with the `--amend` option, but you should never amend a change that has already been pushed. 

How often you commit is up to you, but remember that it's better to have many small, concise commits than one massive commit.

## Rebasing (I couldn't come up with a pun for this one, sue me)

In between you starting your work and finishing, changes may have been made to master. You'll want to rebase and squash unnecessary commits (see [Other Links](#other-links)) before you push.

## Don't Be So Pushy

IF YOU'RE USING A FORK: Simply push directly to your fork.

IF YOU'RE USING A BRANCH: If you haven't pushed before on this branch, you have to add it to the repository using `git push -u origin my_branch_name`. Once the branch is being tracked on the remote, you can simply do `git push origin my_branch_name`.

## You've Got To Be Pulling My Leg

Finally, once your changes are ready, you'll want to submit a pull request on GitHub. This says that you'd like to merge your branch into master. A member of the repository will review your pull request and either ask you to make changes or will accept it. Never merge your own pull request; it should always be merged by somebody who did not participate.

# Other Links

https://8thlight.com/blog/kevin-liddle/2012/09/27/mind-your-git-manners.html (Properly rebasing and pushing your changes)

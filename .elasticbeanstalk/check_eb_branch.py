#!/usr/bin/env python

import sys, yaml

if __name__ == '__main__':
    with open('./.elasticbeanstalk/config.yml', 'r') as file:
        config = yaml.load(file)
        exit(0 if sys.argv[1] in config['branch-defaults'] else 1)

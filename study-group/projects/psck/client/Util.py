import yaml
from argparse import ArgumentParser

def parse_args():
    parser = ArgumentParser()
    parser.add_argument(
        '-c',
        '--config',
        help='Full path to config file.',
        metavar='path'
    )
    return parser.parse_args()


def load_config(name='config'):
    args = parse_args()
    return yaml.load(open(args.config or name + '.yaml', 'r'))


class MyYaml(object):

    stream = open("Setting.yaml", 'r')
    setting = yaml.load(stream)
    node_js_host = setting['node.js_server']['host']
    node_js_port = setting['node.js_server']['port']

    mongodb_host = setting['mongodb_server']['host']
    mongodb_port = setting['mongodb_server']['port']

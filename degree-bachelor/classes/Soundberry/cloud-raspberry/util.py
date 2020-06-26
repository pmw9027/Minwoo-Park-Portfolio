
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
